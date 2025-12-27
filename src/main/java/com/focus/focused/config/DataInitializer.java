package com.focus.focused.config;

import com.focus.focused.entity.*;
import com.focus.focused.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final StoreItemRepository storeItemRepository;

    @Override
    public void run(String... args) {
        if (storeItemRepository.count() == 0) {
            storeItemRepository.saveAll(List.of(
                    // Themes (50 points)
                    new StoreItem(null, "green", "THEME", 50, "#10b981"),
                    new StoreItem(null, "purple", "THEME", 50, "#8b5cf6"),
                    new StoreItem(null, "brown", "THEME", 50, "#78350f"),
                    // Avatars (65 points)
                    new StoreItem(null, "panda", "AVATAR", 65, "🐼"),
                    new StoreItem(null, "koala", "AVATAR", 65, "🐨"),
                    new StoreItem(null, "rabbit", "AVATAR", 65, "🐰")
            ));
        }
    }
}
