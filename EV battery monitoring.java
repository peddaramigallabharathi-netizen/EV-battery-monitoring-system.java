/*
 * EV Battery Monitoring System
 * ESP32/Arduino
 *
 * Sends:
 * Voltage, Current, Temperature, SOC
 *
 * Example:
 * 48.5,12.2,32.5,78.0
 */

const int voltagePin = A0;
const int currentPin = A1;
const int temperaturePin = A2;

void setup() {
    Serial.begin(9600);
}

void loop() {

    int voltageRaw = analogRead(voltagePin);
    int currentRaw = analogRead(currentPin);
    int temperatureRaw = analogRead(temperaturePin);

    // Example conversion values.
    // Calibrate these values for your actual sensors.
    float voltage = (voltageRaw / 1023.0) * 5.0 * 12.0;

    float current = (currentRaw / 1023.0) * 5.0 * 10.0;

    float temperature =
        (temperatureRaw / 1023.0) * 100.0;

    // Example SOC calculation for a 36V nominal battery.
    float soc = ((voltage - 30.0) / (42.0 - 30.0)) * 100.0;

    if (soc < 0)
        soc = 0;

    if (soc > 100)
        soc = 100;

    Serial.print(voltage);
    Serial.print(",");
    Serial.print(current);
    Serial.print(",");
    Serial.print(temperature);
    Serial.print(",");
    Serial.println(soc);

    delay(1000);
}
