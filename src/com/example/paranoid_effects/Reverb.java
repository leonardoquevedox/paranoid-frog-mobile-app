package com.example.paranoid_effects;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.media.audiofx.EnvironmentalReverb;
import android.os.Environment;
import android.util.Log;

import com.example.paranoid_base.ParanoidEffect;

public class Reverb extends ParanoidEffect {
	boolean enableReverb;
	private EnvironmentalReverb reverb;

	public Reverb() {
		int decayTime = 5000;
		short density = 500;
		short diffusion = 500;
		short roomLevel = 0;
		short reverbLevel = 500;
		short reflectionsDelay = 100;
		short reflectionsLevel = 100;
		short reverbDelay = 0;

		reverb = new EnvironmentalReverb(0, 0);
		reverb.setDecayTime(decayTime);
		reverb.setDensity(density);
		reverb.setDiffusion(diffusion);
		reverb.setReverbLevel(reverbLevel);
		reverb.setRoomLevel(roomLevel);
		reverb.setReflectionsDelay(reflectionsDelay);
		reverb.setReflectionsLevel(reflectionsLevel);
		reverb.setReverbDelay(reverbDelay);
	}
	
	public void ReverbLoop() {
		double volume = 20.0;
		float leftVolumeLow = (float) 0.25;
		float rightVolumeLow = (float) 0.25;

		int offset = 0;
		double wetSample;
		short convertedSample;
		try {
			reverb.setEnabled(enableReverb);
			paranoidTrack.attachAuxEffect(reverb.getId());
			paranoidTrack.setAuxEffectSendLevel(100.0f);

			File file = new File(Environment.getExternalStorageDirectory(),
					"ReverbRecording.pcm");
			OutputStream outputStream = new FileOutputStream(file);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
					outputStream);
			DataOutputStream dataOutputStream = new DataOutputStream(
					bufferedOutputStream);

			paranoidTrack.play();
			paranoidTrack.setStereoVolume(leftVolumeLow, rightVolumeLow);
			paranoidRecord.startRecording();
			int i = 0;
			while (recording) {
				offset = paranoidRecord.read(audioData, 0, minBufferSize);
				for (i = 0; i < offset; i++) {
					wetSample = (short) audioData[i] * volume;
					convertedSample = (short) validateShortValue(wetSample);
					audioData[i] = convertedSample;
					dataOutputStream.writeShort(audioData[i]);
				}
				paranoidTrack.write(audioData, 0, offset);
				if (i == minBufferSize) {
					i = 0;
				}
			}
			turnOffEffectLoop();
		} catch (IOException e) {

			e.printStackTrace();
			Log.d("Erro", "Falha na inicializacao dos componentes do loop");
		}
	}

}
