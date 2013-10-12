// Copyright (C) 2013 The Android Open Source Project
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.googlesource.gerrit.plugins.download.command;

import static com.google.gerrit.reviewdb.client.AccountGeneralPreferences.DownloadCommand.CHECKOUT;

import com.google.gerrit.extensions.annotations.Listen;
import com.google.gerrit.server.config.DownloadConfig;
import com.google.inject.Inject;

@Listen
public class CloneCommand extends GitDownloadCommand {
  @Inject
  CloneCommand(DownloadConfig downloadConfig) {
    super(downloadConfig, CHECKOUT);
  }

  @Override
  public String getName() {
    return "clone";
  }

  @Override
  public String getCommand(String url) {
    return "git clone " + url;
  }
}
