JAVA_PATH=/home/ypearson/android-studio/jdk1.7.0_80/bin
JAVAC=$(JAVA_PATH)/javac
JAVA=$(JAVA_PATH)/java

TARGET=HelloWorld

all: clean $(TARGET).class run

%.class: %.java
	$(JAVAC) $<
	$(JAVA)  $(TARGET)

run:

clean:
	@rm -f *.class