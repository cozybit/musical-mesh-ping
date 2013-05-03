package com.cozybit.meshping;

import android.content.Context;
import android.media.MediaPlayer;

class NotePlayer {
	
	private MediaPlayer mAFlatNote;
	private MediaPlayer mANote;
	private MediaPlayer mBFlatNote;
	private MediaPlayer mBNote;
	private MediaPlayer mCSharpNote;
	private MediaPlayer mCNote;
	private MediaPlayer mDNote;
	private MediaPlayer mEFlatNote;
	private MediaPlayer mENote;
	private MediaPlayer mFSharpNote;
	private MediaPlayer mFNote;
	private MediaPlayer mGNote;
	
	public NotePlayer(Context ctx)
	{
		mAFlatNote = MediaPlayer.create(ctx, R.raw.a_flat);
		mANote = MediaPlayer.create(ctx, R.raw.a);
		mBFlatNote = MediaPlayer.create(ctx, R.raw.b_flat);
		mBNote = MediaPlayer.create(ctx, R.raw.b);
		mCSharpNote = MediaPlayer.create(ctx, R.raw.c_sharp);
		mCNote = MediaPlayer.create(ctx, R.raw.c);
		mDNote = MediaPlayer.create(ctx, R.raw.d);
		mEFlatNote = MediaPlayer.create(ctx, R.raw.e_flat);
		mENote = MediaPlayer.create(ctx, R.raw.e);
		mFSharpNote = MediaPlayer.create(ctx, R.raw.f_sharp);
		mFNote = MediaPlayer.create(ctx, R.raw.f);
		mGNote = MediaPlayer.create(ctx, R.raw.g);
	}
	
	public void playNote(String note)
	{
		if (note.equals("a_flat")) 
			mAFlatNote.start();
		else if (note.equals("a")) 
			mANote.start();
		else if (note.equals("b_flat")) 
			mBFlatNote.start();
		else if (note.equals("b")) 
			mBNote.start();
		else if (note.equals("c_sharp")) 
			mCSharpNote.start();
		else if (note.equals("c")) 
			mCNote.start();
		else if (note.equals("d")) 
			mDNote.start();
		else if (note.equals("e_flat")) 
			mEFlatNote.start();
		else if (note.equals("e")) 
			mENote.start();
		else if (note.equals("f_sharp")) 
			mFSharpNote.start();
		else if (note.equals("f")) 
			mFNote.start();
		else if (note.equals("g")) 
			mGNote.start();
	}
	
	
}
