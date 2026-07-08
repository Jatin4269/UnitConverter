package com.jatin.unitconverter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WeightController {

    @GetMapping("/weight")
    public String weightForm() {
        return "weight";
    }

    @PostMapping("/weight")
    public String weightConvert(
            @RequestParam double value,
            @RequestParam String fromUnit,
            @RequestParam String toUnit,
            Model model) {

        double result = convertWeight(value, fromUnit, toUnit);
        model.addAttribute("result", result);
        model.addAttribute("value", value);
        model.addAttribute("fromUnit", fromUnit);
        model.addAttribute("toUnit", toUnit);
        return "weight";
    }

    private double convertWeight(double value, String from, String to) {
        double inGrams = switch (from) {
            case "mg" -> value / 1000;
            case "g" -> value;
            case "kg" -> value * 1000;
            case "oz" -> value * 28.3495;
            case "lb" -> value * 453.592;
            default -> throw new IllegalArgumentException("Unknown unit: " + from);
        };

        return switch (to) {
            case "mg" -> inGrams * 1000;
            case "g" -> inGrams;
            case "kg" -> inGrams / 1000;
            case "oz" -> inGrams / 28.3495;
            case "lb" -> inGrams / 453.592;
            default -> throw new IllegalArgumentException("Unknown unit: " + to);
        };
    }
}