package org.example;

public enum ConsoleType {
    PLAYSTATION,
    XBOX,
    NINTENDO,
    SEGA,
    PC,
}

// redundant record of games, but needed for copy/paste from here
//        app.getInventory().addGame(new Game("Super Mario Bros", ConsoleType.NINTENDO, 1985, 5));
//        app.getInventory().addGame(new Game("The Legend of Zelda", ConsoleType.NINTENDO, 1986, 4));
//        app.getInventory().addGame(new Game("Metroid", ConsoleType.NINTENDO, 1986, 3));
//        app.getInventory().addGame(new Game("Donkey Kong Country", ConsoleType.NINTENDO, 1994, 5));
//        app.getInventory().addGame(new Game("Star Fox", ConsoleType.NINTENDO, 1993, 2));
//        app.getInventory().addGame(new Game("Pokémon Red", ConsoleType.NINTENDO, 1996, 4));
//        app.getInventory().addGame(new Game("Pokémon Blue", ConsoleType.NINTENDO, 1996, 3));
//        app.getInventory().addGame(new Game("GoldenEye 007", ConsoleType.NINTENDO, 1997, 3));
//        app.getInventory().addGame(new Game("Super Smash Bros", ConsoleType.NINTENDO, 1999, 2));
//        app.getInventory().addGame(new Game("Mario Kart 64", ConsoleType.NINTENDO, 1996, 5));
//
//        app.getInventory().addGame(new Game("Halo: Combat Evolved", ConsoleType.XBOX, 2001, 4));
//        app.getInventory().addGame(new Game("Halo 2", ConsoleType.XBOX, 2004, 3));
//        app.getInventory().addGame(new Game("Fable", ConsoleType.XBOX, 2004, 3));
//        app.getInventory().addGame(new Game("Crimson Skies", ConsoleType.XBOX, 2003, 2));
//        app.getInventory().addGame(new Game("Forza Motorsport", ConsoleType.XBOX, 2005, 3));
//        app.getInventory().addGame(new Game("Gears of War", ConsoleType.XBOX, 2006, 4));
//        app.getInventory().addGame(new Game("Gears of War 2", ConsoleType.XBOX, 2008, 3));
//        app.getInventory().addGame(new Game("Kameo: Elements of Power", ConsoleType.XBOX, 2005, 2));
//        app.getInventory().addGame(new Game("Perfect Dark Zero", ConsoleType.XBOX, 2005, 2));
//        app.getInventory().addGame(new Game("Jet Set Radio Future", ConsoleType.XBOX, 2002, 3));
//
//        app.getInventory().addGame(new Game("Crash Bandicoot", ConsoleType.PLAYSTATION, 1996, 4));
//        app.getInventory().addGame(new Game("Spyro the Dragon", ConsoleType.PLAYSTATION, 1998, 3));
//        app.getInventory().addGame(new Game("Final Fantasy VII", ConsoleType.PLAYSTATION, 1997, 4));
//        app.getInventory().addGame(new Game("Gran Turismo", ConsoleType.PLAYSTATION, 1997, 3));
//        app.getInventory().addGame(new Game("Metal Gear Solid", ConsoleType.PLAYSTATION, 1998, 3));
//        app.getInventory().addGame(new Game("Resident Evil 2", ConsoleType.PLAYSTATION, 1998, 2));
//        app.getInventory().addGame(new Game("Tekken 3", ConsoleType.PLAYSTATION, 1997, 4));
//        app.getInventory().addGame(new Game("Tomb Raider", ConsoleType.PLAYSTATION, 1996, 3));
//        app.getInventory().addGame(new Game("Castlevania: Symphony of the Night", ConsoleType.PLAYSTATION, 1997, 2));
//        app.getInventory().addGame(new Game("Silent Hill", ConsoleType.PLAYSTATION, 1999, 3));
//
//        app.getInventory().addGame(new Game("Sonic the Hedgehog", ConsoleType.SEGA, 1991, 5));
//        app.getInventory().addGame(new Game("Sonic the Hedgehog 2", ConsoleType.SEGA, 1992, 4));
//        app.getInventory().addGame(new Game("Streets of Rage", ConsoleType.SEGA, 1991, 3));
//        app.getInventory().addGame(new Game("Golden Axe", ConsoleType.SEGA, 1989, 2));
//        app.getInventory().addGame(new Game("Shinobi III", ConsoleType.SEGA, 1993, 3));
//        app.getInventory().addGame(new Game("Altered Beast", ConsoleType.SEGA, 1988, 2));
//        app.getInventory().addGame(new Game("Phantasy Star IV", ConsoleType.SEGA, 1993, 2));
//        app.getInventory().addGame(new Game("Ecco the Dolphin", ConsoleType.SEGA, 1992, 3));
//        app.getInventory().addGame(new Game("Virtua Fighter 2", ConsoleType.SEGA, 1994, 3));
//        app.getInventory().addGame(new Game("Panzer Dragoon", ConsoleType.SEGA, 1995, 2));
//
//        app.getInventory().addGame(new Game("The Last of Us", ConsoleType.PLAYSTATION, 2013, 5));
//        app.getInventory().addGame(new Game("Uncharted 2", ConsoleType.PLAYSTATION, 2009, 3));
//        app.getInventory().addGame(new Game("God of War", ConsoleType.PLAYSTATION, 2005, 4));
//        app.getInventory().addGame(new Game("Bloodborne", ConsoleType.PLAYSTATION, 2015, 3));
//        app.getInventory().addGame(new Game("Red Dead Redemption", ConsoleType.XBOX, 2010, 4));
//        app.getInventory().addGame(new Game("The Witcher 3", ConsoleType.PLAYSTATION, 2015, 3));
//        app.getInventory().addGame(new Game("Minecraft", ConsoleType.XBOX, 2011, 5));
//        app.getInventory().addGame(new Game("Fortnite", ConsoleType.XBOX, 2017, 3));
//        app.getInventory().addGame(new Game("Super Mario Odyssey", ConsoleType.NINTENDO, 2017, 4));
//        app.getInventory().addGame(new Game("Zelda: Breath of the Wild", ConsoleType.NINTENDO, 2017, 4));
//
//        app.getInventory().addGame(new Game("Doom", ConsoleType.PC, 1993, 5));
//        app.getInventory().addGame(new Game("Quake", ConsoleType.PC, 1996, 4));
//        app.getInventory().addGame(new Game("Half-Life", ConsoleType.PC, 1998, 4));
//        app.getInventory().addGame(new Game("Age of Empires II", ConsoleType.PC, 1999, 3));
//        app.getInventory().addGame(new Game("The Sims", ConsoleType.PC, 2000, 3));
//        app.getInventory().addGame(new Game("Command & Conquer: Red Alert", ConsoleType.PC, 1996, 3));
//        app.getInventory().addGame(new Game("Diablo II", ConsoleType.PC, 2000, 4));
//        app.getInventory().addGame(new Game("StarCraft", ConsoleType.PC, 1998, 3));
//        app.getInventory().addGame(new Game("Baldur’s Gate", ConsoleType.PC, 1998, 2));
//        app.getInventory().addGame(new Game("RollerCoaster Tycoon", ConsoleType.PC, 1999, 4));