package com.jatin.unitconverter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LengthController {

    @GetMapping("/length")



    public String lengthForm() {
        return "length";
    }

    @PostMapping("/length")
    public String lengthConvert(
            @RequestParam double value,
            @RequestParam String fromUnit,
            @RequestParam String toUnit,
            Model model) {

        double result = convertLength(value, fromUnit, toUnit);
        model.addAttribute("result", result);
        model.addAttribute("value", value);
        model.addAttribute("fromUnit", fromUnit);
        model.addAttribute("toUnit", toUnit);
        return "length";
    }

    private double convertLength(double value, String from, String to) {
        double inMeters = switch (from) {
            case "mm" -> value / 1000;
            case "cm" -> value / 100;
            case "m" -> value;
            case "km" -> value * 1000;
            case "inch" -> value * 0.0254;
            case "foot" -> value * 0.3048;
            case "yard" -> value * 0.9144;
            case "mile" -> value * 1609.344;
            default -> throw new IllegalArgumentException("Unknown unit: " + from);
        };

        return switch (to) {
            case "mm" -> inMeters * 1000;
            case "cm" -> inMeters * 100;
            case "m" -> inMeters;
            case "km" -> inMeters / 1000;
            case "inch" -> inMeters / 0.0254;
            case "foot" -> inMeters / 0.3048;
            case "yard" -> inMeters / 0.9144;
            case "mile" -> inMeters / 1609.344;
            default -> throw new IllegalArgumentException("Unknown unit: " + to);
        };
    }
}