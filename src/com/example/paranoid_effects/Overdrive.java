package com.example.paranoid_effects;

import java.io.IOException;

import android.media.audiofx.EnvironmentalReverb;
import android.util.Log;

import com.example.paranoid_base.ParanoidEffect;

public class Overdrive extends ParanoidEffect {
	private boolean enableReverb;
	public boolean isEnableReverb() {
		return enableReverb;
	}

	public void setEnableReverb(boolean enableReverb) {
		this.enableReverb = enableReverb;
	}

	private EnvironmentalReverb reverb;

	public Overdrive() {
		super("OverdriveRecording.pcm");
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

	public void overdriveLoop() {
		double volume = 25.0;
		float leftVolumeLow = (float) 0.5;
		float rightVolumeLow = (float) 0.5;
		int offset = 0;
		double wetSample;
		short convertedSample;
		try {
			reverb.setEnabled(enableReverb);
			paranoidTrack.attachAuxEffect(reverb.getId());
			paranoidTrack.setAuxEffectSendLevel(100.0f);
			paranoidTrack.play();
			paranoidTrack.setStereoVolume(leftVolumeLow, rightVolumeLow);
			paranoidRecord.startRecording();
			int i = 0;
			while (recording) {
				offset = paranoidRecord.read(audioData, 0, minBufferSize);
				for (i = 0; i < offset; i++) {
					wetSample = (short) audioData[i] * volume;
					if (wetSample < -32767.0f) {
						wetSample = -32767.0f;
					}
					if (wetSample > 32767.0f) {
						wetSample = 32767.0f;
					}
					convertedSample = (short) wetSample;
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
