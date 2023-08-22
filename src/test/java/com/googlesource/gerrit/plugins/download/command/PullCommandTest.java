// Copyright (C) 2023 The Android Open Source Project
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

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;

import com.google.gerrit.server.config.DownloadConfig;
import com.google.gerrit.server.git.GitRepositoryManager;
import com.googlesource.gerrit.plugins.download.DownloadCommandTest;
import org.eclipse.jgit.lib.Config;
import org.junit.Test;

public class PullCommandTest extends DownloadCommandTest {
  private static final String TEST_URL = "unit.test/";
  private static final String TEST_REF = "origin/main";
  private static final String TEST_ID = "none";
  private static final String PULL_COMMAND_SUFFIX = TEST_URL + " " + TEST_REF;

  @Test
  public void buildPullCommand() {
    PullCommand pullCommand = newPullCommand(defaultGerritConfig());

    String actual = pullCommand.getCommand(TEST_URL, TEST_REF, TEST_ID);

    assertThat(actual).isEqualTo("git pull " + PULL_COMMAND_SUFFIX);
  }

  @Test
  public void buildPullCommandWithRecurseSubmodules() {
    Config cfg = defaultGerritConfig();
    cfg.setBoolean("download", null, "recurseSubmodules", true);
    PullCommand pullCommand = newPullCommand(cfg);

    String actual = pullCommand.getCommand(TEST_URL, TEST_REF, TEST_ID);

    assertThat(actual).isEqualTo("git pull --recurse-submodules " + PULL_COMMAND_SUFFIX);
  }

  private PullCommand newPullCommand(Config cfg) {
    GitRepositoryManager gitRepositoryManagerMock = mock(GitRepositoryManager.class);

    return new PullCommand(cfg, new DownloadConfig(cfg), gitRepositoryManagerMock);
  }

  private Config defaultGerritConfig() {
    Config cfg = new Config();
    cfg.setString("download", null, "command", "pull");

    return cfg;
  }
}