package de.stickmc.ms.manager;


import de.stickmc.ms.manager.modules.*;

public class ModuleLoader {

    public static void loadModules() {
        BanModule.loadModule();
        KickModule.loadModule();
        MaintenanceModule.loadModule();
        MotdModule.loadModule();
        ChatModule.loadModule();
        MuteModule.loadModule();
        GetIpModule.loadModule();
        BroadcastModule.loadModule();
        PingModule.loadModule();
        ReportModule.loadModule();
        NotifyModule.loadModule();
        OnlineModule.loadModule();
        OnlineTimeModule.loadModule();
        TeamChatModule.loadModule();
        HelpModule.loadModule();
        HubModule.loadModule();
        CMDBlockModule.loadModule();
        DiscordModule.loadModule();
        AutoBroadcastModule.loadModule();
        ClanModule.loadModule();
    }
}
