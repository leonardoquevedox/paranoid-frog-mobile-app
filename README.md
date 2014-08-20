ParanoidFrog
============
The Paranoid Frog Live consists on an audio loopback (mic-to-speakers) that applies effects based on the device's inclination.
Putting that way it sounds confusing, but it's actually pretty simple: the app gets the audio data from an input plugged into the device (ideally, a guitar, but it also works for basses, keyboards, and basically any electric instrument), and applies digital sound effects (such as Reverb, Wahwah, Overdrive, Tremelo...). 
Although the algorithms were right thinking on simplicity and mobile devices limitations, the jackpot of Paranoid Frog Live, is that it integrates the accelerometer data into the effects!
There are three regular effects, and three accelerometer based effects already implemented:
* ``AcceleroFootSwitch`` - Selects which effect the user wants to use based on the guitar inclination range.
* ``AcceleroTremelo`` - Changes the tremelo oscillation frequency based on the same factor as above.
* ``AcceleroWahWah`` - Opening and closing the sound channel, it allows the user to control the WahWah cycles just moving the guitar up and down.
* ``Wahah, Tremelo and Distortion`` - Regular effects, design to have a lower processing cost.

Latency
-------
On the Android audio world, things aren't always rainbows and butterflies: In counterpart to the really easy to use APIs provided by the AOSP team to manipulate audio data, there is an widely known issue regarding the latency that makes any app similar to the Paranoid Frog pretty much useless for now. 
Due to this issue (widely known as the issue #3434), the app is on its beta stage, but already being improved to work with newer audio libraries.

Device compatibility
--------------------
As per the graphic elements, and audio APIs used the Paranoid Frog Live project has been tested and worked with API levels between 10 to 16 (in short terms, from Gingerbread to Jelly Bean). 
Although the app has only been tested with these levels, there is a chance that it works on higher API levels). On the next few weeks, the app will be tested on a larger range of devices, in order to evaluate and expand the compatibility levels.

Contact
-------
Any doubts, suggestions or cheap chats regarding the Paranoid Frog Live project can be forward to my e-mail address:
pac.leo@hotmail.com
