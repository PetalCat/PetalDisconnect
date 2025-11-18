# PetalDisconnect

PetalDisconnect is a lightweight client-side utility mod that gives you full control over how and when you disconnect from Minecraft servers.  
Whether you need an instant panic key, automatic disconnects when the game loses focus, or optional smart auto-reconnect behavior, this mod keeps you safe, fast, and in control.

---

## ✨ Features

### 🔌 Quick Disconnect Keybind  
Instantly drop your server connection using a dedicated keybind.  
Perfect for panic exits, privacy, or quickly switching tasks.

> **Note:** The quick-disconnect keybind is configured under:  
> **Options → Controls → Keybinds**, not in the mod’s settings menu.

---

### 🪟 Auto-Disconnect on Lost Focus  
Optionally disconnects the moment your game window loses focus.  
Ideal for multitaskers, frequent alt-tabbers, or anyone who wants the game to pause safely when not active.

---

### 🔄 Optional Auto-Reconnect  
If enabled, the mod can automatically reconnect **only when PetalDisconnect itself caused the disconnect**.  
Normal disconnects (server kicks or manually leaving) will never trigger auto-reconnect.

---

### ⚙️ In-Game Settings Menu  
Accessible through Mod Menu, offering clean toggles for:

- Enable Quick Disconnect  
- Disconnect on Focus Loss  
- Auto Reconnect  

> **Reminder:** The quick-disconnect keybind is still configured under **Controls → Keybinds**.

---

## 📦 Installation

1. Install **Fabric Loader**  
2. Install **Fabric API**  
3. Download the latest version of **PetalDisconnect**  
4. Drop the `.jar` into your `mods/` folder

This mod is **client-side only** — it does **not** need to be installed on servers.

---

## 🛠 Compatibility

- Minecraft **1.21.1**  
- Fabric Loader **0.16.7+**  
- Works with **Mod Menu**  
- No server-side installation required  
- Compatible with most client-side mods

---

## 🧪 How It Works

PetalDisconnect listens for:

- Window focus changes  
- Client disconnect events  
- Your quick-disconnect keybind  

It then intelligently decides whether to disconnect or reconnect based on:

- Your configuration  
- Whether *PetalDisconnect* triggered the disconnect  
- Whether a valid server entry is available  

---

## 🔧 Configuration

You can configure options through:

- **Mod Menu → PetalDisconnect → Configure**  
- Or directly via:  
  `~/.minecraft/config/petaldisconnect.json`

---

## 🤝 Credits

Made with ❤️ by **PetalCat**
