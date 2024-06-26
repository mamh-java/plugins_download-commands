// Copyright (C) 2015 The Android Open Source Project
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

import com.google.gerrit.extensions.annotations.Exports;
import com.google.gerrit.extensions.config.CloneCommand;
import com.google.inject.AbstractModule;

public class CloneCommandsModule extends AbstractModule {
  @Override
  protected void configure() {
//    bind(CloneCommand.class)
//        .annotatedWith(Exports.named("Clone"))
//        .to(com.googlesource.gerrit.plugins.download.command.CloneCommand.class);

    bind(CloneCommand.class)
        .annotatedWith(Exports.named("Clone"))
        .to(CloneWithCommitMsgHook.class);
  }
}
