package com.jatin.unitconverter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TemperatureController {

    @GetMapping("/temperature")
    public String temperatureForm() {
        return "temperature";
    }

    @PostMapping("/temperature")
    public String temperatureConvert(
            @RequestParam double value,
            @RequestParam String fromUnit,
            @RequestParam String toUnit,
            Model model) {

        double result = convertTemperature(value, fromUnit, toUnit);
        model.addAttribute("result", result);
        model.addAttribute("value", value);
        model.addAttribute("fromUnit", fromUnit);
        model.addAttribute("toUnit", toUnit);
        return "temperature";
    }

    private double convertTemperature(double value, String from, String to) {
        double inCelsius = switch (from) {
            case "celsius" -> value;
            case "fahrenheit" -> (value - 32) * 5.0 / 9.0;
            case "kelvin" -> value - 273.15;
            default -> throw new IllegalArgumentException("Unknown unit: " + from);
        };

        return switch (to) {
            case "celsius" -> inCelsius;
            case "fahrenheit" -> (inCelsius * 9.0 / 5.0) + 32;
            case "kelvin" -> inCelsius + 273.15;
            default -> throw new IllegalArgumentException("Unknown unit: " + to);
        };

    }
}
