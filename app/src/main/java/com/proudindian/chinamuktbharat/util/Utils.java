package com.proudindian.chinamuktbharat.util;

import com.google.gson.reflect.TypeToken;
import com.proudindian.chinamuktbharat.model.AlternateChinaAppsInfo;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

    private Utils() {
    }

    private static Map<String, String> restrictedApps = new HashMap<>();
    private static ArrayList<FilterChinaAppsHelper.ApplicationInfo> installedApps = new ArrayList<>();
    private static ArrayList<FilterChinaAppsHelper.ApplicationInfo> SystemApps = new ArrayList<>();
    private static boolean isMenuFacturerChina;
    private static String menufacturerName;

    public static AlternateChinaAppsInfo getAlternateChinaAppsInfo() {
        return alternateChinaAppsInfo;
    }

    public static void setAlternateChinaAppsInfo() {
        String jsonString = "{\n" +
                "  \"root\" : [\n" +
                "    {\n" +
                "    \"ChinaAppName\": \"CamScanner\",\n" +
                "    \"alternatives\": [\n" +
                "      {\n" +
                "        \"AppName\": \"Adobe Scan\",\n" +
                "        \"PackageName\": \"com.adobe.scan.android\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"AppName\": \"Microsoft Office Lens\",\n" +
                "        \"PackageName\": \"com.microsoft.office.officelens\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"AppName\": \"ScanPro App\",\n" +
                "        \"PackageName\": \"net.doo.snap\"\n" +
                "      }\n" +
                "    ]\n" +
                "    },\n" +
                "     {\n" +
                "    \"ChinaAppName\": \"ShareIt , Xender\",\n" +
                "    \"alternatives\": [\n" +
                "      {\n" +
                "        \"AppName\": \"Google Files\",\n" +
                "        \"PackageName\": \"com.google.android.apps.nbu.files\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"AppName\": \"JioSwitch\",\n" +
                "        \"PackageName\": \"com.reliance.jio.jioswitch\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"AppName\": \"ShareAll\",\n" +
                "        \"PackageName\": \"com.pnd.shareall\"\n" +
                "      }\n" +
                "    ]\n" +
                "  \n" +
                "  },\n" +
                "  {\n" +
                "    \"ChinaAppName\": \"UC Browser\",\n" +
                "    \"alternatives\": [\n" +
                "      {\n" +
                "        \"AppName\": \"Google Chrome\",\n" +
                "        \"PackageName\": \"com.android.chrome\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"AppName\": \"Mozilla FireFox\",\n" +
                "        \"PackageName\": \"org.mozilla.firefox\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"AppName\": \"Opera with VPN\",\n" +
                "        \"PackageName\": \"com.opera.browser\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "   {\n" +
                "    \"ChinaAppName\": \"TikTok , Helo , Likee\",\n" +
                "    \"alternatives\": [\n" +
                "      {\n" +
                "        \"AppName\": \"Mitron\",\n" +
                "        \"PackageName\": \"com.mitron.tv\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"AppName\": \"Vimeo\",\n" +
                "        \"PackageName\": \"com.vimeo.android.videoapp\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"AppName\": \"Periscope\",\n" +
                "        \"PackageName\": \"tv.periscope.android\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "     {\n" +
                "    \"ChinaAppName\": \"Tencent PUBG\",\n" +
                "    \"alternatives\": [\n" +
                "      {\n" +
                "        \"AppName\": \"Fortnight\",\n" +
                "        \"PackageName\": \"com.epicgames.fortnite\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"AppName\": \"Free Fire\",\n" +
                "        \"PackageName\": \"com.vimeo.android.videoapp\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"ChinaAppName\": \"ClubFactory\",\n" +
                "    \"alternatives\": [\n" +
                "      {\n" +
                "        \"AppName\": \"Myntra\",\n" +
                "        \"PackageName\": \"com.myntra.android\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"AppName\": \"SnapDeal\",\n" +
                "        \"PackageName\": \"com.snapdeal.main\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"AppName\": \"ShopClues\",\n" +
                "        \"PackageName\": \"com.shopclues\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "   {\n" +
                "    \"ChinaAppName\": \"AppLock\",\n" +
                "    \"alternatives\": [\n" +
                "      {\n" +
                "        \"AppName\": \"MaxLock\",\n" +
                "        \"PackageName\": \"de.Maxr1998.xposed.maxlock\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"AppName\": \"ToolWiz Applock\",\n" +
                "        \"PackageName\": \"com.cleanwiz.applock\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"AppName\": \"Norton App Lock\",\n" +
                "        \"PackageName\": \"com.symantec.applock\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "     {\n" +
                "    \"ChinaAppName\": \"BeautyPlus , BeautyCam , B612\",\n" +
                "    \"alternatives\": [\n" +
                "      {\n" +
                "        \"AppName\": \"PicsArt\",\n" +
                "        \"PackageName\": \"com.picsart.studio\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"AppName\": \"Snapseed\",\n" +
                "        \"PackageName\": \"com.niksoftware.snapseed\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"AppName\": \"Adobe Lightroom\",\n" +
                "        \"PackageName\": \"com.adobe.lrmobile\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"ChinaAppName\": \"QU Video , PowerDirector , FilmoraGo\",\n" +
                "    \"alternatives\": [\n" +
                "      {\n" +
                "        \"AppName\": \"Photostory\",\n" +
                "        \"PackageName\": \"kr.co.nnngomstudio.jphoto2\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },{\n" +
                "    \"ChinaAppName\": \"Turbo VPN\",\n" +
                "    \"alternatives\": [\n" +
                "      {\n" +
                "        \"AppName\": \"Nord VPN\",\n" +
                "        \"PackageName\": \"com.nordvpn.android\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"AppName\": \"Proton VPN\",\n" +
                "        \"PackageName\": \"ch.protonvpn.android\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"AppName\": \"OpenVPN\",\n" +
                "        \"PackageName\": \"net.openvpn.openvpn\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"ChinaAppName\": \"Parallel Space\",\n" +
                "    \"alternatives\": [\n" +
                "      {\n" +
                "        \"AppName\": \"Shelter\",\n" +
                "        \"PackageName\": \"net.typeblog.shelter\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"AppName\": \"Island\",\n" +
                "        \"PackageName\": \"com.oasisfeng.island\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"AppName\": \"Clone App\",\n" +
                "        \"PackageName\": \"com.cloneapp.parallelspace.dualspace\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"ChinaAppName\": \"Zoom\",\n" +
                "    \"alternatives\": [\n" +
                "      {\n" +
                "        \"AppName\": \"Google Meet\",\n" +
                "        \"PackageName\": \"com.google.android.apps.meetings\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"AppName\": \"Microsoft Teams\",\n" +
                "        \"PackageName\": \"com.microsoft.teams\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"AppName\": \"Webx\",\n" +
                "        \"PackageName\": \"com.cisco.webex.meetings\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"ChinaAppName\": \"U-Dictionary\",\n" +
                "    \"alternatives\": [\n" +
                "      {\n" +
                "        \"AppName\": \"Dictionary.com\",\n" +
                "        \"PackageName\": \"com.dictionary\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"AppName\": \"English Dictionary\",\n" +
                "        \"PackageName\": \"livio.pack.lang.en_US\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"AppName\": \"Oxford Dictionary\",\n" +
                "        \"PackageName\": \"com.mobisystems.msdict.embedded.wireless.oxford.dictionaryofenglish\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "    {\n" +
                "    \"ChinaAppName\": \"WPS Office\",\n" +
                "    \"alternatives\": [\n" +
                "      {\n" +
                "        \"AppName\": \"Microsoft Office Suite\",\n" +
                "        \"PackageName\": \"com.microsoft.office.officehubrow\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"AppName\": \"Only Office\",\n" +
                "        \"PackageName\": \"com.onlyoffice.documents\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "  ]\n" +
                "}";
        Gson gson = new Gson();
        Type type = new TypeToken<AlternateChinaAppsInfo>() {
        }.getType();
        Utils.alternateChinaAppsInfo = gson.fromJson(jsonString, type);
    }

    private static AlternateChinaAppsInfo alternateChinaAppsInfo;

    public static void setInstalledApps(ArrayList<FilterChinaAppsHelper.ApplicationInfo> installedApps) {
        Utils.installedApps = installedApps;
    }

    public static ArrayList<FilterChinaAppsHelper.ApplicationInfo> getSystemApps() {
        return SystemApps;
    }


    public static void setSystemApps(ArrayList<FilterChinaAppsHelper.ApplicationInfo> systemApps) {
        SystemApps = systemApps;
    }

    public static void setIsMenuFacturerChina(boolean isMenuFacturerChina) {
        Utils.isMenuFacturerChina = isMenuFacturerChina;
    }

    public static String getMenufacturerName() {
        return menufacturerName;
    }

    public static void setMenufacturerName(String menufacturerName) {
        Utils.menufacturerName = menufacturerName;
    }

    public static ArrayList<FilterChinaAppsHelper.ApplicationInfo> getChinaInstalledApps() {
        return installedApps;
    }

    public static ArrayList<FilterChinaAppsHelper.ApplicationInfo> getChinaSystemAppsApp() {
        return SystemApps;
    }

    public static void setAlternateToChinaApps(ArrayList<FilterChinaAppsHelper.ApplicationInfo> alternateToChinaApps) {
    }

    public static boolean isIsMenuFacturerChina() {
        return isMenuFacturerChina;
    }

    public static void InitRestrictedApps() {
        restrictedApps.put("com.security.antivirus.sefeanti", "");
        restrictedApps.put("com.apusapps.browser", "");
        restrictedApps.put("com.meitu.airbrush.vivo", "");
        restrictedApps.put("com.magicv.airbrush", "");
        restrictedApps.put("com.meitu.airvid", "");
        restrictedApps.put("com.alibaba.aliexpresshd", "");
        restrictedApps.put("com.alibaba.intl.android.apps.poseidon", "");
        restrictedApps.put("com.snowwhiteapps.downloader", "");
        restrictedApps.put("com.domobile.applockwatcher", "");
        restrictedApps.put("com.domobile.applock.lite", "");
        restrictedApps.put("com.dewmobile.kuaiya.zproj.applockz", "");
        restrictedApps.put("com.ngame.allstar.eu", "");
        restrictedApps.put("com.lilithgame.sgame.gp.oss", "");
        restrictedApps.put("com.meitu.boxxcam", "");
        restrictedApps.put("com.baidu.searchbox", "");
        restrictedApps.put("com.baidu.BaiduMap", "");
        restrictedApps.put("com.meitu.meiyancamera", "");
        restrictedApps.put("com.commsource.beautyplus", "");
        restrictedApps.put("sg.bigo.live", "");
        restrictedApps.put("com.cm.browser.downloader.adblock", "");
        restrictedApps.put("com.intsig.camscanner", "");
        restrictedApps.put("com.intsig.BCRLite", "");
        restrictedApps.put("com.igg.castleclash", "");
        restrictedApps.put("com.jetstartgames.chess", "");
        restrictedApps.put("com.tencent.godgame", "");
        restrictedApps.put("com.xinlukou.metroman", "");
        restrictedApps.put("com.hcg.cok.gp", "");
        restrictedApps.put("com.hcg.ctw.gp", "");
        restrictedApps.put("com.elex.coq.gp", "");
        restrictedApps.put("club.fromfactory", "");
        restrictedApps.put("com.limsky.ramcleaner", "");
        restrictedApps.put("com.DU.Cleaner.antivirus.cleanphone", "");
        restrictedApps.put("com.dating.android", "");
        restrictedApps.put("com.alibaba.android.rimet", "");
        restrictedApps.put("com.alibaba.dingtalk.global", "");
        restrictedApps.put("com.tool.fileexplorer.filemanager.filetransfer", "");
        restrictedApps.put("com.file.manager.filebrowser", "");
        restrictedApps.put("com.dewmobile.kuaiya.filez", "");
        restrictedApps.put("com.flashkeyboardtheme", "");
        restrictedApps.put("com.dc.hwsj", "");
        restrictedApps.put("com.diandian.gog", "");
        restrictedApps.put("sg.bigo.hellotalk", "");
        restrictedApps.put("app.buzz.share", "");
        restrictedApps.put("app.buzz.share.lite", "");
        restrictedApps.put("com.hcg.tos.gp", "");
        restrictedApps.put("com.funplus.kingofavalon", "");
        restrictedApps.put("com.kwai.video", "");
        restrictedApps.put("com.gtarcade.lod", "");
        restrictedApps.put("com.longtech.lastwars.gp", "");
        restrictedApps.put("com.live.royal", "");
        restrictedApps.put("com.videochat.livu", "");
        restrictedApps.put("com.tencent.ludosuperstar", "");
        restrictedApps.put("com.yottagames.mafiawar", "");
        restrictedApps.put("com.netease.mail", "");
        restrictedApps.put("com.meitu.makeup", "");
        restrictedApps.put("com.meitu.meipaimv", "");
        restrictedApps.put("com.mt.mtxx.mtxx", "");
        restrictedApps.put("com.mi.global.bbs", "");
        restrictedApps.put("com.micredit.in.gp", "");
        restrictedApps.put("com.mi.global.shop", "");
        restrictedApps.put("com.mobile.legends", "");
        restrictedApps.put("com.musicplayer.musica.musicapps.playermusic", "");
        restrictedApps.put("com.dewmobile.kuaibao.gp", "");
        restrictedApps.put("com.newsdog", "");
        restrictedApps.put("com.nono.android", "");
        restrictedApps.put("com.meitu.oxygen", "");
        restrictedApps.put("com.lbe.parallel.intl", "");
        restrictedApps.put("com.cyberlink.youperfect", "");
        restrictedApps.put("com.yubitu.android.YubiCollage", "");
        restrictedApps.put("com.pleco.chinesesystem", "");
        restrictedApps.put("com.meitu.beautyplusme", "");
        restrictedApps.put("com.beautyplus.pomelo.filters.photo", "");
        restrictedApps.put("com.tencent.mobileqq", "");
        restrictedApps.put("com.tencent.androidqqmail", "");
        restrictedApps.put("com.tencent.qqmusic", "");
        restrictedApps.put("com.dewmobile.kuaiya.recorder", "");
        restrictedApps.put("com.romwe", "");
        restrictedApps.put("com.lenovo.anyshare.gps", "");
        restrictedApps.put("shareit.lite", "");
        restrictedApps.put("com.dewmobile.kuaiya.zproj.screenlockz", "");
        restrictedApps.put("com.meitu.wheecam", "");
        restrictedApps.put("com.zzkko", "");
        restrictedApps.put("com.sina.weibo", "");
        restrictedApps.put("com.taobao.taobao", "");
        restrictedApps.put("com.zhiliaoapp.musically", "");
        restrictedApps.put("com.ss.android.ugc.trill.go", "");
        restrictedApps.put("com.zhiliaoapp.musically.go", "");
        restrictedApps.put("com.zhiliao.musically.livewallpaper", "");
        restrictedApps.put("free.vpn.unblock.proxy.turbovpn", "");
        restrictedApps.put("com.ucturbo", "");
        restrictedApps.put("com.CricChat.intl", "");
        restrictedApps.put("com.uc.iflow", "");
        restrictedApps.put("com.UCMobile.intl", "");
        restrictedApps.put("com.uc.browser.en", "");
        restrictedApps.put("com.youdao.hindict", "");
        restrictedApps.put("com.asiainno.uplive", "");
        restrictedApps.put("com.uc.vmate", "");
        restrictedApps.put("com.netqin.ps", "");
        restrictedApps.put("com.ss.android.ugc.boomlite", "");
        restrictedApps.put("com.ss.android.ugc.boom", "");
        restrictedApps.put("com.ss.android.ugc.boom.livewallpaper", "");
        restrictedApps.put("com.HIsecurity.antivirus.litepro", "");
        restrictedApps.put("com.quvideo.vivavideo.lite", "");
        restrictedApps.put("com.ushareit.watchit", "");
        restrictedApps.put("cn.wps.moffice_eng", "");
        restrictedApps.put("cn.wps.moffice_extra", "");
        restrictedApps.put("cn.wps.moffice_i18n", "");
        restrictedApps.put("cn.wps.pdf", "");
        restrictedApps.put("cn.wps.pdf.fillsign", "");
        restrictedApps.put("cn.wps.moffice_premium", "");
        restrictedApps.put("com.etekcity.vesyncplatform", "");
        restrictedApps.put("com.qidian.Int.reader", "");
        restrictedApps.put("com.tencent.mm", "");
        restrictedApps.put("com.weico.international", "");
        restrictedApps.put("cn.xender", "");
        restrictedApps.put("com.cyberlink.youcammakeup", "");
        restrictedApps.put("com.dewmobile.kuaiya.play", "");
        restrictedApps.put("com.dewmobile.kuaiya.game.airhockey.play", "");
        restrictedApps.put("com.dewmobile.kuaiya.paintpad.play", "");
        restrictedApps.put("com.dewmobile.zapyago", "");
        restrictedApps.put("com.dewmobile.game", "");
        restrictedApps.put("com.dewmobile.kuaiya.web", "");
        restrictedApps.put("com.zw.zombieworld.gp", "");
        restrictedApps.put("com.banggood.client", "");
        restrictedApps.put("com.alibaba.aliexpress.itao", "");
        restrictedApps.put("com.mipay.in.wallet", "");
        restrictedApps.put("com.duokan.phone.remotecontroller", "");
        restrictedApps.put("com.quvideo.xiaoying", "");
        restrictedApps.put("us.zoom.videomeetings", "");
        restrictedApps.put("us.zoom.videomeetings4intune", "");
        restrictedApps.put("video.like", "");
        restrictedApps.put("video.like.lite", "");
        restrictedApps.put("com.kwai.global.video.social.kwaigo", "");
        restrictedApps.put("com.linecorp.b612.android", "");
        restrictedApps.put("com.cyberlink.powerdirector.DRA140225_01", "");
        restrictedApps.put("com.wondershare.filmorago", "");
        restrictedApps.put("com.lbe.parallel.intl.arm64", "");
    }

    public static Map<String, String> getRestrictedAppsList() {
        return restrictedApps;
    }
}
