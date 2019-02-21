package com.example.yangjie.mydemo;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * 。
 *
 * @author 迅动互联 2017/12/19 新建.
 */
public class SampleApplication extends TinkerApplication {

    public SampleApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "com.example.yangjie.mydemo.SampleApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }
}
