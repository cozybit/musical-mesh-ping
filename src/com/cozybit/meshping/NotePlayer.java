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
	
	final static String NOTE_A_FLAT = "a_flat";
	final static String NOTE_A = "a";
	final static String NOTE_B_FLAT = "b_flat";
	final static String NOTE_B = "b";
	final static String NOTE_C_SHARP = "c_sharp";
	final static String NOTE_C = "c";
	final static String NOTE_D = "d";
	final static String NOTE_E_FLAT = "e_flat";
	final static String NOTE_E = "e";
	final static String NOTE_F_SHARP = "f_sharp";
	final static String NOTE_F = "f";
	final static String NOTE_G = "g";
	
	public void playNote(String note)
	{
		if (note.equals(NOTE_A_FLAT)) 
			mAFlatNote.start();
		else if (note.equals(NOTE_A)) 
			mANote.start();
		else if (note.equals(NOTE_B_FLAT)) 
			mBFlatNote.start();
		else if (note.equals(NOTE_B)) 
			mBNote.start();
		else if (note.equals(NOTE_C_SHARP)) 
			mCSharpNote.start();
		else if (note.equals(NOTE_C)) 
			mCNote.start();
		else if (note.equals(NOTE_D)) 
			mDNote.start();
		else if (note.equals(NOTE_E_FLAT)) 
			mEFlatNote.start();
		else if (note.equals(NOTE_E)) 
			mENote.start();
		else if (note.equals(NOTE_F_SHARP)) 
			mFSharpNote.start();
		else if (note.equals(NOTE_F)) 
			mFNote.start();
		else if (note.equals(NOTE_G)) 
			mGNote.start();
	}
	
	final static String C_MAJOR = "C major";
	final static String C_MINOR = "C minor";
	final static String C_ADD_FIVE = "C +5th";
	final static String C_SUB_FIVE = "C -5th";
	final static String C_6TH = "C 6th";
	final static String C_MINOR_6TH = "C minor 6th";
	final static String C_7TH = "C 7th";
	final static String C_MINOR_7TH = "C minor 7th";
	final static String C_MAJOR_7TH = "C major 7th";
	final static String C_7_ADD_5 = "C seven (+5)";
	final static String C_7_SUB_5 = "C seven (-5)";
	final static String C_7_ADD_9 = "C seven (+9)";
	final static String C_7_SUB_9 = "C seven (-9)";
	final static String C_MAJOR_7_ADD_9 = "C major 7 (add 9)";
	final static String C_9TH = "C 9th";
	final static String C_9TH_ADD_5 = "C nine (+5)";
	final static String C_9TH_SUB_5 = "C nine (-5)";
	final static String C_11TH = "C 11th";
	final static String C_AUG_11TH = "C aug 11th";
	final static String C_13_SUB_9 = "C thirteen (-9)";
	final static String C_DIM = "C dim";
	
	public static final String[] C_CHORD_NAMES =
	{
		C_MAJOR,
		C_MINOR,
		C_ADD_FIVE,
		C_SUB_FIVE,
		C_6TH,
		C_MINOR_6TH,
		C_7TH,
		C_MINOR_7TH,
		C_MAJOR_7TH,
		C_7_ADD_5,
		C_7_SUB_5,
		C_7_ADD_9,
		C_7_SUB_9,
		C_MAJOR_7_ADD_9,
		C_9TH,
		C_9TH_ADD_5,
		C_9TH_SUB_5,
		C_11TH,
		C_AUG_11TH,
		C_13_SUB_9,
		C_DIM,
	};
	
	public String[] getChord(String chordName)
	{
		if ( C_MAJOR.equals(chordName) ) {
			return new String[] { NOTE_C, NOTE_E, NOTE_G };
		}
		else if ( C_MINOR.equals(chordName) ) {
			return new String [] { NOTE_C, NOTE_E_FLAT, NOTE_G };
		}
		else if ( C_ADD_FIVE.equals(chordName) ) {
			return new String [] { NOTE_C, NOTE_E, NOTE_A_FLAT };	
		}
		else if ( C_SUB_FIVE.equals(chordName) ) {
			return new String [] { NOTE_C, NOTE_E, NOTE_F_SHARP};	
		}
		else if ( C_6TH.equals(chordName) ) {
			return new String [] { NOTE_C, NOTE_E, NOTE_G, NOTE_A };	
		}
		else if ( C_MINOR_6TH.equals(chordName) ) {
			return new String [] { NOTE_C, NOTE_E_FLAT, NOTE_G, NOTE_A };	
		}
		else if ( C_7TH.equals(chordName) ) {
			return new String [] { NOTE_C, NOTE_E, NOTE_G, NOTE_B_FLAT };	
		}
		else if ( C_MINOR_7TH.equals(chordName) ) {
			return new String[] { NOTE_C, NOTE_E_FLAT, NOTE_G, NOTE_B_FLAT };	
		}
		else if ( C_MAJOR_7TH.equals(chordName) ) {
			return new String [] { NOTE_C, NOTE_E, NOTE_G, NOTE_B };	
		}
		else if ( C_7_ADD_5.equals(chordName) ) {
			return new String [] { NOTE_C, NOTE_E, NOTE_A_FLAT, NOTE_B_FLAT };	
		}
		else if ( C_7_SUB_5.equals(chordName) ) {
			return new String [] { NOTE_C, NOTE_E, NOTE_F_SHARP, NOTE_B_FLAT };	
		}
		else if ( C_7_ADD_9.equals(chordName) ) {
			return new String [] { NOTE_C, NOTE_E, NOTE_G, NOTE_B_FLAT, NOTE_E_FLAT };	
		}
		else if ( C_7_SUB_9.equals(chordName) ) {
			return new String [] { NOTE_C, NOTE_E, NOTE_G, NOTE_B_FLAT, NOTE_C_SHARP };	
		}
		else if ( C_MAJOR_7_ADD_9.equals(chordName) ) {
			return new String [] { NOTE_C, NOTE_E, NOTE_G, NOTE_B, NOTE_D };	
		}
		else if ( C_9TH.equals(chordName) ) {
			return new String [] { NOTE_C, NOTE_E, NOTE_G, NOTE_B_FLAT, NOTE_D  };	
		}
		else if ( C_9TH_ADD_5.equals(chordName) ) {
			return new String [] { NOTE_C, NOTE_E, NOTE_A_FLAT, NOTE_B_FLAT, NOTE_D  };	
		}
		else if ( C_9TH_SUB_5.equals(chordName) ) {
			return new String [] { NOTE_C, NOTE_E, NOTE_F_SHARP, NOTE_B_FLAT, NOTE_D  };	
		}
		else if ( C_11TH.equals(chordName) ) {
			return new String [] { NOTE_C, NOTE_E, NOTE_G, NOTE_B_FLAT, NOTE_D, NOTE_F  };	
		}
		else if ( C_AUG_11TH.equals(chordName) ) {
			return new String [] { NOTE_C, NOTE_E, NOTE_G, NOTE_B_FLAT, NOTE_F_SHARP  };	
		}
		else if ( C_13_SUB_9.equals(chordName) ) {
			return new String [] { NOTE_C, NOTE_E, NOTE_B_FLAT, NOTE_C_SHARP, NOTE_A };	
		}
		else if ( C_DIM.equals(chordName) ) {
			return new String [] { NOTE_C, NOTE_E_FLAT, NOTE_F_SHARP, NOTE_A };	
		}
		
		IllegalArgumentException ex = new IllegalArgumentException("Unknown chord name!");
		throw ex;
	}
	
}
