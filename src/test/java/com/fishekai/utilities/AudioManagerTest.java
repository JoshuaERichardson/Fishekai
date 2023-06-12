package com.fishekai.utilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.fishekai.utilities.Console.pause;
import static org.junit.jupiter.api.Assertions.*;

class AudioManagerTest {
    AudioManager audioManager = new AudioManager();

    @BeforeEach
    void setUp() {
        audioManager.loadAudioFiles();
    }
    @Test
    void testLoadSoundEffect() {
        AudioManager audioManager = new AudioManager();
        assertDoesNotThrow(() -> audioManager.loadSoundEffect("testSound", "/sounds/testSound.wav"));
    }

    @Test
    void testPlaySoundEffect() {
        AudioManager audioManager = new AudioManager();
        audioManager.loadSoundEffect("testSound", "/sounds/testSound.wav");
        assertDoesNotThrow(() -> audioManager.playSoundEffect("testSound"));
    }

    @Test
    void testLoadMusic() {
        AudioManager audioManager = new AudioManager();
        assertDoesNotThrow(() -> audioManager.loadMusic("/sounds/testMusic.wav"));
    }

    @Test
    void testPlayMusic() {
        AudioManager audioManager = new AudioManager();
        audioManager.loadMusic("/sounds/testMusic.wav");
        assertDoesNotThrow(() -> audioManager.playMusic(true));
    }

    @Test
    void testStopMusic() {
        AudioManager audioManager = new AudioManager();
        audioManager.loadMusic("/sounds/testMusic.wav");
        audioManager.playMusic(true);
        assertDoesNotThrow(audioManager::stopMusic);
    }

    @Test
    void testSetMusicVolume() {
        AudioManager audioManager = new AudioManager();
        audioManager.loadMusic("/sounds/testMusic.wav");
        assertDoesNotThrow(() -> audioManager.setMusicVolume(0.5f));
    }

    @Test
    void testSetSoundEffectsVolume() {
        AudioManager audioManager = new AudioManager();
        audioManager.loadSoundEffect("testSound", "/sounds/testSound.wav");
        assertDoesNotThrow(() -> audioManager.setSoundEffectsVolume(0.5f));
    }

    @Test
    void testIncreaseSoundEffectsVolume() {
        AudioManager audioManager = new AudioManager();
        audioManager.loadSoundEffect("testSound", "/sounds/testSound.wav");
        assertDoesNotThrow(audioManager::increaseSoundEffectsVolume);
    }

    @Test
    void testDecreaseSoundEffectsVolume() {
        AudioManager audioManager = new AudioManager();
        audioManager.loadSoundEffect("testSound", "/sounds/testSound.wav");
        assertDoesNotThrow(audioManager::decreaseSoundEffectsVolume);
    }

    @Test
    void testRandomEat() {
        AudioManager audioManager = new AudioManager();
        audioManager.loadSoundEffect("eat1", "/sounds/eat1.wav");
        audioManager.loadSoundEffect("eat2", "/sounds/eat2.wav");
        audioManager.loadSoundEffect("eat3", "/sounds/eat3.wav");
        audioManager.loadSoundEffect("eat4", "/sounds/eat4.wav");
        assertDoesNotThrow(audioManager::randomEat);
    }
}


