package com.melodystream.service;

import com.melodystream.exception.EmptyQueueException;
import com.melodystream.model.Audio;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

public class PlayerService {
    
    private LinkedList<Audio> playQueue;
    private Stack<Audio> history;
    private Audio current;

    public PlayerService() {
        this.playQueue = new LinkedList<>();
        this.history = new Stack<>();
        this.current = null;
    }

    // افزودن به انتهای صف
    public void addToQueue(Audio audio) {
        playQueue.addLast(audio);
    }

    // افزودن به ابتدای صف
    public void addToFront(Audio audio) {
        playQueue.addFirst(audio);
    }

     // پخش آهنگ بعدی
    public Audio playNext() throws EmptyQueueException {

        if (playQueue.isEmpty()) {
            throw new EmptyQueueException("Play queue is empty");
        }

        if (current != null) {
            history.push(current);
        }

        current = playQueue.removeFirst();
        current.play();
        return current;
    }

    // برگشت به آهنگ قبلی
    public Audio playPrevious() throws EmptyQueueException {

        if (history.isEmpty()) {
            throw new EmptyQueueException("No previous song");
        }

        if (current != null) {
            playQueue.addFirst(current);
        }

        current = history.pop();
        current.play();
        return current;
    }

    // Shuffle صف پخش
    public void shuffle() {
        Collections.shuffle(playQueue);
    }

    public Audio getCurrent() {
        return current;
    }
}