package com.cozybit.meshping;

import com.samsung.chord.ChordManager;
import com.samsung.chord.IChordChannel;
import com.samsung.chord.IChordChannelListener;
import com.samsung.chord.IChordManagerListener;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MeshPingActivity extends Activity implements OnClickListener 
{
	
	static final String TAG = "MeshPingActivity";

	private static final String CHORD_CHANNEL_NAME = "COZYBIT_MESH_PING";
	
	ImageButton mPingButton;	
	NotePlayer mNotePlayer = null;
	ChordManager mChord = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mNotePlayer = new NotePlayer(getApplicationContext());
		
		mPingButton = (ImageButton) findViewById(R.id.pingButton);
		mPingButton.setOnClickListener(this);
	}
	
	private void setupChord()
	{
		if ( mChord != null )
			return;
		
		mChord = ChordManager.getInstance(getApplicationContext());
		
		mChord.setTempDirectory(getCacheDir().getAbsolutePath());
        mChord.setHandleEventLooper(getMainLooper());
		
		int start = mChord.start(ChordManager.INTERFACE_TYPE_WIFI, mChordManagerListener);
		Log.i(TAG, "ChordManager.start() = " + (start == ChordManager.ERROR_NONE) + " (" + start + ")");
	}
	
	private void tearDownChord()
	{
		mPingChannel = null;
		mPublicChannel = null;
		
		mChord.stop();
		mChord = null;
	}
	
	IChordManagerListener mChordManagerListener = new IChordManagerListener() {

		@Override
		public void onError(int arg0) {
			Log.e(TAG, "Chord failed: " + arg0);
			mPingChannel = null;
			mPublicChannel = null;
		}

		@Override
		public void onNetworkDisconnected() {
		}

		@Override
		public void onStarted(String name, int reason) 
		{
			if (IChordManagerListener.STARTED_BY_RECONNECTION == reason)
			{
				Log.e(TAG, "STARTED_BY_RECONNECTION");
				return;
			}
			
			/*if ( mChord.getJoinedChannel(CHORD_CHANNEL_NAME) != null )
			{
				Log.w(TAG, "Chord claims we're already in the channel re-joining!");
				
				mChord.leaveChannel(CHORD_CHANNEL_NAME);
				mChord.leaveChannel(ChordManager.PUBLIC_CHANNEL);
			}*/
			
			mPublicChannel = mChord.joinChannel(ChordManager.PUBLIC_CHANNEL, mChordChannelListener);
			mPingChannel = mChord.joinChannel(CHORD_CHANNEL_NAME, mChordChannelListener);
		}
	};
	
	IChordChannel mPingChannel = null;
	IChordChannel mPublicChannel = null;
	
	IChordChannelListener mChordChannelListener = new IChordChannelListener() {
		
		@Override
		public void onDataReceived(String fromNode, String fromChannel, String payloadType, byte[][] payload) {

			if ( payloadType.equals("PING") )
			{
				IChordChannel channel = mChord.getJoinedChannel(CHORD_CHANNEL_NAME);
				if ( channel == null )
					return;

				String note = StringUtils.fromUtf8(payload[0]);
				mNotePlayer.playNote(note);
			}
		}

		@Override
		public void onFileChunkReceived(String arg0, String arg1, String arg2,
				String arg3, String arg4, String arg5, long arg6, long arg7) { }

		@Override
		public void onFileChunkSent(String arg0, String arg1, String arg2,
				String arg3, String arg4, String arg5, long arg6, long arg7,
				long arg8) { }

		@Override
		public void onFileFailed(String arg0, String arg1, String arg2,
				String arg3, String arg4, int arg5) { }

		@Override
		public void onFileReceived(String arg0, String arg1, String arg2,
				String arg3, String arg4, String arg5, long arg6, String arg7) { }

		@Override
		public void onFileSent(String arg0, String arg1, String arg2, String arg3,
				String arg4, String arg5) { }

		@Override
		public void onFileWillReceive(String arg0, String arg1, String arg2,
				String arg3, String arg4, String arg5, long arg6) { }

		@Override
		public void onNodeLeft(String fromNode, String fromChannel) {
            Log.v(TAG, "onNodeLeft(), fromNode : " + fromNode + ", fromChannel : "
                    + fromChannel);
		}

		@Override
		public void onNodeJoined(String fromNode, String fromChannel) {
            Log.v(TAG, "onNodeJoined(), fromNode : " + fromNode + ", fromChannel : "
                    + fromChannel);
		}
	};
	
	@Override
	protected void onResume() {
		super.onResume();
		setupChord();
		Log.i(TAG, "onResume()");
	};
	
	@Override
	protected void onStop() {
		super.onStop();
		Log.i(TAG, "onStop()");
	};
	
	@Override
	protected void onPause() {
		super.onPause();
		tearDownChord();
		Log.i(TAG, "onPause()");
	};	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true; 
	}

	@Override
	public void onClick(View v) {
		
		Log.i(TAG, "PING!");
		
		if ( mPingChannel == null )
			return;
		
		int idx = 1;
		String[] e_minor_7 = { "e", "b", "d", "g" };
		
		mNotePlayer.playNote(e_minor_7[0]);
		
		for ( String node : mPingChannel.getJoinedNodeList() )
		{
			idx = (idx + 1) % e_minor_7.length;
			mPingChannel.sendData(node, "PING", new byte[][] { StringUtils.toUtf8(e_minor_7[idx]) });
		}
	}
}
