/*
 * Copyright (C) 2018 Marcus Pimenta
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.pimenta.bestv.manager;

import android.util.Log;

import com.pimenta.bestv.repository.remote.api.ip.InfoApi;
import com.pimenta.bestv.repository.entity.IpInfo;

import java.io.IOException;

import javax.inject.Inject;

/**
 * Created by marcus on 11-02-2018.
 */
public class DeviceManagerImpl implements DeviceManager {

    private static final String TAG = "DeviceManagerImpl";

    private IpInfo mIpInfo;
    private InfoApi mInfoApi;

    @Inject
    public DeviceManagerImpl(InfoApi infoApi) {
        mInfoApi = infoApi;
    }

    @Override
    public String getCountryCode() {
        try {
            if (mInfoApi == null) {
                mIpInfo = mInfoApi.getIpInfo().execute().body();
            }
        } catch (IOException e) {
            Log.e(TAG, "Failed to get the ip info", e);
        }

        return mIpInfo != null ? mIpInfo.getCountryCode() : null;
    }
}