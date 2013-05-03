package com.cozybit.meshping;

import com.samsung.chord.ChordManager;
import com.samsung.chord.IChordChannel;
import com.samsung.chord.IChordChannelListener;
import com.samsung.chord.IChordManagerListener;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MeshPingActivity extends Activity 
	implements OnClickListener
			, ChordService.IChordServiceListener 
{
    // **********************************************************************
    // Using Service
    // **********************************************************************
    private ChordService mChordService = null;

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        	Log.d(TAG,  "onServiceConnected()");
            ChordService.ChordServiceBinder binder = (ChordService.ChordServiceBinder)service;
            mChordService = binder.getService();
            try {
                mChordService.initialize(MeshPingActivity.this);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            mChordService.start(ChordManager.INTERFACE_TYPE_WIFI);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub
            Log.i(TAG, "onServiceDisconnected()");
            mChordService = null;
        }
    };

    public void bindChordService() {
        Log.i(TAG, "bindChordService()");
        if (mChordService == null) {
            Intent intent = new Intent(
					"com.cozybit.meshping.ChordService.SERVICE_BIND");
            bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        }
    }

    private void unbindChordService() {
        Log.i(TAG, "unbindChordService()");

        if (null != mChordService) {
            unbindService(mConnection);
        }
        mChordService = null;
    }

    private void startService() {
        Log.i(TAG, "startService()");
        Intent intent = new Intent("com.cozybit.meshping.ChordService.SERVICE_START");
        startService(intent);
    }

    private void stopService() {
        Log.i(TAG, "stopService()");
        Intent intent = new Intent("com.cozybit.meshping.ChordService.SERVICE_STOP");
        stopService(intent);
    }
	
	static final String TAG = "MeshPingActivity";

	private static final String CHORD_CHANNEL_NAME = "COZYBIT_MESH_PING";
	
	ImageButton mPingButton;
	
	NotePlayer mNotePlayer = null;
	ChordManager mChord = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
        startService();
        bindChordService();
		
		mNotePlayer = new NotePlayer(getApplicationContext());
		
		mPingButton = (ImageButton) findViewById(R.id.pingButton);
		mPingButton.setOnClickListener(this);
	}
	
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindChordService();
        stopService();
    }
	
	void onDataReceived(String fromNode, String fromChannel, String payloadType, byte[][] payload) {

		if ( payloadType.equals("PING") )
		{
			String note = StringUtils.fromUtf8(payload[0]);
			mNotePlayer.playNote(note);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true; 
	}

	@Override
	public void onClick(View v) {
		
		Log.i(TAG, "PING!");
		
		int idx = 1;
		String[] e_minor_7 = { "e", "b", "d", "g" };
		
		mNotePlayer.playNote(e_minor_7[0]);
		
		for ( String node : mChordService.getJoinedNodeList(CHORD_CHANNEL_NAME) )
		{
			idx = (idx + 1) % e_minor_7.length;
			mChordService.sendData(CHORD_CHANNEL_NAME, node, "PING", StringUtils.toUtf8(e_minor_7[idx]));
		}
	}

	@Override
	public void onReceiveMessage(String node, String channel, String dataType, String message) {
		
		if ( ! dataType.equals("PING") )
			return;
		
		mNotePlayer.playNote(message);
	}

	@Override
	public void onFileWillReceive(String node, String channel, String fileName,
			String exchangeId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFileProgress(boolean bSend, String node, String channel,
			int progress, String exchangeId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFileCompleted(int reason, String node, String channel,
			String exchangeId, String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNodeEvent(String node, String channel, boolean bJoined) {
		
		if (channel == ChordManager.PUBLIC_CHANNEL)
		{
			mChordService.joinChannel(CHORD_CHANNEL_NAME);
		}
		
	}

	@Override
	public void onNetworkDisconnected() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdateNodeInfo(String nodeName, String ipAddress) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnectivityChanged() {
		// TODO Auto-generated method stub
		
	}
}
