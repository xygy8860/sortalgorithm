/*
 * Copyright (C) 2016 Naman Dwivedi
 *
 * Licensed under the GNU General Public License v3
 *
 * This is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 */

package com.duy.algorithm.algorithms.algo;


import android.app.Activity;

import com.duy.algorithm.R;
import com.duy.algorithm.algorithms.AlgorithmThread;
import com.duy.algorithm.algorithms.SortAlgorithmThread;
import com.duy.algorithm.customview.SortView;
import com.duy.algorithm.utils.ArrayUtils;

public class CocktailShakerSortThread extends SortAlgorithmThread {

    int[] array;

    public CocktailShakerSortThread(SortView sortView, Activity activity) {
        this.mSortView = sortView;
        this.activity = activity;
    }


    public void cocktailShakerSort() {
        int i = 0;
        while (i < array.length / 2) {
            for (int j = i; j < array.length - i - 1; j++) {
                showLog("Doing iteration - " + i);
                onTrace(j);
                sleep();

                if (array[j] > array[j + 1]) {

                    showLog("Swapping " + array[j] + " and " + array[j + 1]);
                    onSwapping(j, j + 1);

                    ArrayUtils.swap(array, j, j + 1);

                    onSwapped();
                }
            }
            for (int j = array.length - i - 1; j > i; j--) {
                showLog("Doing iteration - " + i);
                onTrace(j);
                sleep();

                if (array[j] < array[j - 1]) {

                    onSwapping(j, j - 1);
                    showLog("Swapping " + array[j] + " and " + array[j - 1]);

                    ArrayUtils.swap(array, j, j - 1);

                    onSwapped();
                }
            }
            i++;
        }
    }

    public void sort() {
        showLog(getString(R.string.original_arr), array);

        cocktailShakerSort();
        showLog(getString(R.string.arr_sorted), array);

        onCompleted();
    }


    @Override
    public void onDataReceived(Object data) {
        super.onDataReceived(data);
        this.array = (int[]) data;
    }

    @Override
    public void onMessageReceived(String message) {
        super.onMessageReceived(message);
        if (message.equals(AlgorithmThread.COMMAND_START_ALGORITHM)) {
            startExecution();
            sort();
        }
    }
}
