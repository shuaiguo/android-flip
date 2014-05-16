#!/bin/bash
gradle build; adb install -r build/apk/*-debug-unaligned.apk; adb shell am start -n com.wandoujia.huntforapps/com.wandoujia.huntforapps.CardFlipActivity
