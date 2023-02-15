/*
 * Copyright 2022 The TensorFlow Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// PermissionsFragment is not instantiating!
/*
E/AndroidRuntime: FATAL EXCEPTION: main
    Process: example.application.com, PID: 643
    androidx.fragment.app.Fragment$InstantiationException: Unable to instantiate fragment example.application.com.ui.fragments.PermissionsFragment: make sure class name exists
        at androidx.fragment.app.FragmentFactory.loadFragmentClass(FragmentFactory.java:97)
        at androidx.fragment.app.Fragment.instantiate(Fragment.java:661)
        at androidx.fragment.app.FragmentContainer.instantiate(FragmentContainer.java:57)
        at androidx.fragment.app.FragmentManager$3.instantiate(FragmentManager.java:507)
        at androidx.navigation.fragment.FragmentNavigator.createFragmentTransaction(FragmentNavigator.kt:250)
        at androidx.navigation.fragment.FragmentNavigator.navigate(FragmentNavigator.kt:185)
        at androidx.navigation.fragment.FragmentNavigator.navigate(FragmentNavigator.kt:164)
        at androidx.navigation.NavGraphNavigator.navigate(NavGraphNavigator.kt:83)
        at androidx.navigation.NavGraphNavigator.navigate(NavGraphNavigator.kt:49)
        at androidx.navigation.NavController.navigateInternal(NavController.kt:267)
        at androidx.navigation.NavController.navigate(NavController.kt:1731)
        at androidx.navigation.NavController.navigate(NavController.kt:1557)
        at androidx.navigation.NavController.navigate(NavController.kt:1484)
        at androidx.navigation.NavController.navigate(NavController.kt:1466)
        at androidx.navigation.NavController.navigate(NavController.kt:1449)
        at example.application.com.ui.home.HomeFragment$1.onClick(HomeFragment.java:47)
        at android.view.View.performClick(View.java:7288)
        at android.view.View.performClickInternal(View.java:7258)
        at android.view.View.access$4000(View.java:808)
        at android.view.View$PerformClick.run(View.java:28019)
        at android.os.Handler.handleCallback(Handler.java:883)
        at android.os.Handler.dispatchMessage(Handler.java:100)
        at android.os.Looper.loop(Looper.java:214)
        at android.app.ActivityThread.main(ActivityThread.java:7615)
        at java.lang.reflect.Method.invoke(Native Method)
        at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:492)
        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:964)
     Caused by: java.lang.ClassNotFoundException: example.application.com.ui.fragments.PermissionsFragment
        at java.lang.Class.classForName(Native Method)
        at java.lang.Class.forName(Class.java:454)
        at androidx.fragment.app.FragmentFactory.loadClass(FragmentFactory.java:53)
        at androidx.fragment.app.FragmentFactory.loadFragmentClass(FragmentFactory.java:94)
        at androidx.fragment.app.Fragment.instantiate(Fragment.java:661) 
        at androidx.fragment.app.FragmentContainer.instantiate(FragmentContainer.java:57) 
        at androidx.fragment.app.FragmentManager$3.instantiate(FragmentManager.java:507) 
        at androidx.navigation.fragment.FragmentNavigator.createFragmentTransaction(FragmentNavigator.kt:250) 
        at androidx.navigation.fragment.FragmentNavigator.navigate(FragmentNavigator.kt:185) 
        at androidx.navigation.fragment.FragmentNavigator.navigate(FragmentNavigator.kt:164) 
        at androidx.navigation.NavGraphNavigator.navigate(NavGraphNavigator.kt:83) 
        at androidx.navigation.NavGraphNavigator.navigate(NavGraphNavigator.kt:49) 
        at androidx.navigation.NavController.navigateInternal(NavController.kt:267) 
        at androidx.navigation.NavController.navigate(NavController.kt:1731) 
        at androidx.navigation.NavController.navigate(NavController.kt:1557) 
        at androidx.navigation.NavController.navigate(NavController.kt:1484) 
        at androidx.navigation.NavController.navigate(NavController.kt:1466) 
        at androidx.navigation.NavController.navigate(NavController.kt:1449) 
        at example.application.com.ui.home.HomeFragment$1.onClick(HomeFragment.jav
 */
package example.application.com.ui.fragments

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import example.application.com.R

private val PERMISSIONS_REQUIRED = arrayOf(Manifest.permission.RECORD_AUDIO)

/**
 * The sole purpose of this fragment is to request permissions and, once granted, display the
 * audio fragment to the user.
 */
class PermFragment : Fragment() {

    val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(context, "Permission request granted", Toast.LENGTH_LONG).show()
                navigateToAudioFragment()
            } else {
                Toast.makeText(context, "Permission request denied", Toast.LENGTH_LONG).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.RECORD_AUDIO
            ) == PackageManager.PERMISSION_GRANTED -> {
                navigateToAudioFragment()
            }
            else -> {
                requestPermissionLauncher.launch(
                    Manifest.permission.RECORD_AUDIO)
            }
        }
    }

    private fun navigateToAudioFragment() {
        lifecycleScope.launchWhenStarted {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main).navigate(R.id.action_permissions_to_audio)
        }
    }

    companion object {
        /** Convenience method used to check if all permissions required by this app are granted */
        fun hasPermissions(context: Context) = PERMISSIONS_REQUIRED.all {
            ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }
    }
}
