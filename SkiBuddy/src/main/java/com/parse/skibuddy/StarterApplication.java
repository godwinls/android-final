/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.skibuddy;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class StarterApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    // Enable Local Datastore.
    Parse.enableLocalDatastore(this);

    // Add your initialization code here
    Parse.initialize(this, "du1xZEAelipOn1x1HonpwAvUn2IDM3TRQuoSHpwe", "T7VsDcjjZ9uPPBlV1ujQ7NL6jGxgWPSu7jfH2E8Y");

    ParseUser.enableAutomaticUser();
    ParseACL defaultACL = new ParseACL();
    // Optionally enable public read access.
    // defaultACL.setPublicReadAccess(true);
              ParseACL.setDefaultACL(defaultACL, true);

              ParseUser user = new ParseUser();
              user.setUsername("my name");
              user.setPassword("my pass");
              user.setEmail("email@example.com");

        // other fields can be set just like with ParseObject
              user.put("phone", "650-555-0000");

              user.signUpInBackground(new SignUpCallback() {
                  public void done(ParseException e) {
              if (e == null) {
                  // Hooray! Let them use the app now.
              } else {
                  // Sign up didn't succeed. Look at the ParseException
                  // to figure out what went wrong
              }
          }
      });
  }
}
