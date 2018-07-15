---
title: python入门
date: 2017-10-11 20:13:10
tags: python programing
categories: Python
---
```Python
class Car:##类名
    def __init__(self):  ##方法名 self 为Car对象实例的引用
        self.speed = 0
        self.odometer = 0
        self.time = 0

    def say_state(self):
        print("I'm going {} kph!".format(self.speed))

    def accelerate(self):
        self.speed += 5

    def brake(self):
        self.speed -= 5

    def step(self):
        self.odometer += self.speed
        self.time += 1

    def average_speed(self):
        return self.odometer / self.time


if __name__ == '__main__': ##入口函数
    my_car = Car()##获得对象实例
    print("I'm a car!")
    while True:  ##循环语句
        action = input("What should I do? [A]ccelerate, [B]rake, "
            "show [O]dometer, or show average [S]peed?").upper()####字母字符（串）中大小写转换函数--upper() 和 lower()
        if action not in "ABOS" or len(action) != 1:
            print("I don't know how to do that")
            continue
        if action == 'A':
            my_car.accelerate()
        elif action == 'B':
            my_car.brake()
        elif action == 'O':
            print("The car has driven {} kilometers".format(my_car.odometer))
        elif action == 'S':
            print("The car's average speed was {} kph".format(my_car.average_speed()))##{}的内容在format方法中
        my_car.step()
        my_car.say_state()
```
输出
```txt
"C:\Program Files\Python36-32\python.exe" C:/Users/15581/PycharmProjects/untitled6/Car.py
I'm a car!
What should I do? [A]ccelerate, [B]rake, show [O]dometer, or show average [S]peed?A
I'm going 5 kph!
What should I do? [A]ccelerate, [B]rake, show [O]dometer, or show average [S]peed?B
I'm going 0 kph!
What should I do? [A]ccelerate, [B]rake, show [O]dometer, or show average [S]peed?S
The car's average speed was 2.5 kph
I'm going 0 kph!
What should I do? [A]ccelerate, [B]rake, show [O]dometer, or show average [S]peed?O
The car has driven 5 kilometers
I'm going 0 kph!
What should I do? [A]ccelerate, [B]rake, show [O]dometer, or show average [S]peed?

```
