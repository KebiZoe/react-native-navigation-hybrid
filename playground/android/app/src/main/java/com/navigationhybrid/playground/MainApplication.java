package com.navigationhybrid.playground;

import android.support.multidex.MultiDexApplication;

import com.facebook.common.logging.FLog;
import com.facebook.drawee.view.DraweeView;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;
import com.navigationhybrid.NavigationHybridPackage;
import com.navigationhybrid.ReactBridgeManager;
import com.oblador.vectoricons.VectorIconsPackage;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.taihua.hud.HUDReactPackage;

import java.util.Arrays;
import java.util.List;


/**
 * Created by Listen on 2017/11/17.
 */

public class MainApplication extends MultiDexApplication implements ReactApplication {

    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                    new MainReactPackage(),
                    new NavigationHybridPackage(),
                    new VectorIconsPackage(),
                    new HUDReactPackage()
            );
        }

        @Override
        protected String getJSMainModuleName() {
            return "playground/index";
        }
    };

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }

        SoLoader.init(this, false);

        ReactBridgeManager bridgeManager = ReactBridgeManager.get();
        bridgeManager.install(getReactNativeHost());

        // register native modules
        bridgeManager.registerNativeModule("OneNative", OneNativeFragment.class);
        bridgeManager.registerNativeModule("NativeModal", NativeModalFragment.class);

        refWatcher = LeakCanary.install(this);

        bridgeManager.setMemoryWatcher(object -> refWatcher.watch(object));

        DraweeView.setGlobalLegacyVisibilityHandlingEnabled(true);
        FLog.setMinimumLoggingLevel(FLog.INFO);
    }

    private RefWatcher refWatcher;

}
