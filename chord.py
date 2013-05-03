# -*- python -*-

from wavebender import damped_wave, compute_samples, write_wavefile

a_flat_5     = 830.609
g_5          = 783.991
f_sharp_5    = 739.989
f5           = 698.456
e5           = 659.255
e_flat_5     = 622.254
d5           = 587.330
c_sharp_5    = 554.365
c5           = 523.251
b4           = 493.883
b_flat_4     = 466.164
a_4          = 440.000

notes = {
	"a_flat"  : a_flat_5  ,
	"g"       : g_5       ,
	"f_sharp" : f_sharp_5 ,
	"f"       : f5        ,
	"e"       : e5        ,
	"e_flat"  : e_flat_5  ,
	"d"       : d5        ,
	"c_sharp" : c_sharp_5 ,
	"c"       : c5        ,
	"b"       : b4        ,
	"b_flat"  : b_flat_4  ,
	"a"       : a_4       ,
}

rate=44100
time=5.0
amplitude=0.4
samplewidth=2 # bytes

for freq in notes:

	channels = ((damped_wave(notes[freq], rate, amplitude),),)

	# convert the channel functions into waveforms
	samples = compute_samples(channels, rate * time)

	# write the samples to a file
	write_wavefile(freq + ".wav", samples, rate * time, len(channels), samplewidth, rate)
