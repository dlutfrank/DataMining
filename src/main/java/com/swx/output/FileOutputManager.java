package com.swx.output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

public class FileOutputManager {
	private static final String EOF = "com.swx.output.EOFEOFEOFEOF";
	private LinkedBlockingQueue<String> mQueue;
	private String mFileName;
	private BufferedWriter mBuffWriter;
	private volatile boolean quitNow = false;
	private Byte[] obj = new Byte[0];
	private volatile boolean isStarted = false;

	public FileOutputManager() {
		mQueue = new LinkedBlockingQueue<String>();
	}

	public void init(String outputFileName) throws IOException {
		mFileName = outputFileName;
		mBuffWriter = new BufferedWriter(new FileWriter(new File(mFileName)));
	}

	public void start() {
		if(isStarted) {
			return;
		}
		synchronized(obj) {
			if (!isStarted) {
				new Thread(outputTask).start();				
			}
		}
	}

	private Runnable outputTask = new Runnable() {

		@Override
		public void run() {
			isStarted = true;
			String content = null;
			while (true) {
				try {
					content = mQueue.take();
					if (!content.equals(EOF)) {
						mBuffWriter.write(content);
					} else {
						if (quitNow || mQueue.isEmpty()) {
							mBuffWriter.flush();
							mBuffWriter.close();
							isStarted = false;
							break;
						} else {
							mQueue.offer(EOF);
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

	};

	private void stopOutput(boolean quitNow) {
		synchronized (obj) {
			this.quitNow = quitNow;
			mQueue.offer(EOF);
		}
	}

	public void stopOutput() {
		stopOutput(true);
	}

	public void stopOutputSafe() {
		stopOutput(false);
	}

	public void addTask(String str) {
		if (str == null || str.isEmpty()) {
			return;
		}
		mQueue.add(str);
	}
}
