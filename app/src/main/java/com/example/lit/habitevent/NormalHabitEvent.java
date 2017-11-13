/*
 * Copyright 2017 Max Schafer, Ammar Mahdi, Riley Dixon, Steven Weikai Lu, Jiaxiong Yang
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.example.lit.habitevent;

import android.support.annotation.NonNull;

import com.example.lit.exception.HabitFormatException;
import com.example.lit.location.HabitLocation;

/**
 * Created by ammar on 21/10/17.
 */

public class NormalHabitEvent extends HabitEvent{
    public NormalHabitEvent(String name){super(name);}
    public NormalHabitEvent(String name, String comment)throws HabitFormatException {super(name, comment);}
    public NormalHabitEvent(String name, String comment, HabitLocation location)throws HabitFormatException {super(name, comment,location);}

    @Override
    public int compareTo(@NonNull Object o) {
        return 0;
    }
}
