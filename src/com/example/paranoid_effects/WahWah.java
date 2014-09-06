package com.example.paranoid_effects;

import java.io.IOException;

import android.util.Log;

import com.example.paranoid_base.ParanoidEffect;

public class WahWah extends ParanoidEffect {
	public WahWah() {
		super("WahWahRecording.pcm");
	}
	
	boolean asc;

	public void wahWahLoop() {
		try {
			paranoidTrack.play();
			int i = 0;
			int o = 0;
			double wetSample;
			short convertedSample;
			double volume = 20.0;
			paranoidRecord.startRecording();
			while (recording) {
				o = paranoidRecord.read(audioData, 0, minBufferSize);
				for (i = 0; i < o; i++) {
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
					//Log.d("AxisY",String.valueOf(axisY));
					paranoidTrack.setStereoVolume(axisY, axisY);
				}
				paranoidTrack.write(audioData, 0, o);
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
