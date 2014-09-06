package com.example.paranoid_effects;

import java.io.IOException;

import android.util.Log;

import com.example.paranoid_base.ParanoidEffect;

public class AcceleroTremelo extends ParanoidEffect {

	public AcceleroTremelo() {
		super("AcceleroWahWahRecording.pcm");
	}

	public void AcceleroWahWahLoop() {
		boolean ascend = true;
		try {

			paranoidTrack.play();
			int i = 0;
			int o = 0;
			double wetSample;
			short convertedSample;
			double volume = 0.8;
			paranoidRecord.startRecording();
			while (recording) {
				o = paranoidRecord.read(audioData, 0, minBufferSize);
				for (i = 0; i < o; i++) {
					if (ascend) {
						volume += axisY;
					} else {
						volume = volume - axisY;
					}
					if (volume > 0.0 && volume < 1.0) {
						ascend = true;
					}
					if (volume > 30.0 || volume == 30.0) {
						ascend = false;
					}

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
					dataOutputStream.writeShort(audioData[i]);
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
