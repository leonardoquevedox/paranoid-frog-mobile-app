package com.example.paranoid_base;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

public class ParanoidEffect {

	protected int paranoidSampleRate;
	protected boolean recording;
	protected Float axisY;
	protected int minBufferSize;
	protected AudioRecord paranoidRecord;
	protected AudioTrack paranoidTrack;
	protected short[] audioData;

	protected File file;
	protected OutputStream outputStream;
	protected BufferedOutputStream bufferedOutputStream;
	protected DataOutputStream dataOutputStream;

	public ParanoidEffect(String fileName) {
		try {
			this.setupOutputToFile(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Log.d("Failure!", "Failure on output file inicialization!");
		}
		this.paranoidSampleRate = 44100;
		this.recording = false;
		this.minBufferSize = AudioTrack.getMinBufferSize(paranoidSampleRate,
				AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT);
		this.paranoidRecord = new AudioRecord(
				MediaRecorder.AudioSource.DEFAULT, paranoidSampleRate,
				AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT,
				minBufferSize);
		this.paranoidTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
				paranoidSampleRate, AudioFormat.CHANNEL_CONFIGURATION_MONO,
				AudioFormat.ENCODING_PCM_16BIT, minBufferSize,
				AudioTrack.MODE_STREAM);
		this.axisY = 0f;
		this.audioData = new short[minBufferSize];
	}

	private void setupOutputToFile(String fileName)
			throws FileNotFoundException {
		File file = new File(Environment.getExternalStorageDirectory(),
				fileName);
		file.setWritable(true);
		this.outputStream = new FileOutputStream(file);
		this.bufferedOutputStream = new BufferedOutputStream(outputStream);
		this.dataOutputStream = new DataOutputStream(bufferedOutputStream);
	}

	public void closeStreams() {
		try {
			this.outputStream.close();
			this.bufferedOutputStream.close();
			this.dataOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
			Log.d("Failure!", "Failure on closing the output streams!");
		}

	}

	public boolean isRecording() {
		return recording;
	}

	public void setRecording(boolean recording) {
		this.recording = recording;
	}

	public Float getAxisY() {
		return axisY;
	}

	public void setAxisY(Float axisY) {
		this.axisY = axisY;
	}

	public void playRecord() {
		// TODO Play file with specific effect
		File file = new File(Environment.getExternalStorageDirectory(),
				"teste.pcm");
		file.setWritable(true);
		int shortSizeInBytes = Short.SIZE / Byte.SIZE;
		int bufferSizeInBytes = (int) (file.length() / shortSizeInBytes);
		short[] audioData = new short[bufferSizeInBytes];
		int i = 0;
		try {
			InputStream inputStream = new FileInputStream(file);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(
					inputStream);
			DataInputStream dataInputStream = new DataInputStream(
					bufferedInputStream);
			while (dataInputStream.available() > 0) {
				audioData[i] = dataInputStream.readShort();
				i++;
			}
			dataInputStream.close();
			AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
					paranoidSampleRate, AudioFormat.CHANNEL_CONFIGURATION_MONO,
					AudioFormat.ENCODING_PCM_16BIT, bufferSizeInBytes,
					AudioTrack.MODE_STREAM);

			audioTrack.play();
			audioTrack.write(audioData, 0, bufferSizeInBytes);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void turnOffEffectLoop() {
		this.closeStreams();
		paranoidRecord.stop();
		paranoidTrack.stop();
	}

	protected double validateShortValue(double sample) {
		if (sample < Short.MIN_VALUE) {
			return (double) (Short.MIN_VALUE);
		}
		if (sample < Short.MAX_VALUE) {
			return (double) (Short.MAX_VALUE);
		}
		return sample;
	}

}
