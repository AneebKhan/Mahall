package utilities;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.itf.phatbooskiandroid.classes.CustomApplication;

/**
 * Created by zahmed on 12/11/2018.
 * ITF
 */

public class ResourceUtils {

    // Custom method to get a string resource by resource name
    public static String getStringFromResourcesByName(Context context, String resourceName) {
        /*
            getPackageName()
                Return the name of this application's package.
        */
        // Get the application package name
        String packageName = context.getPackageName();

        /*
            getResources()
                Return a Resources instance for your application's package.
        */
        /*
            public int getIdentifier (String name, String defType, String defPackage)
                Return a resource identifier for the given resource name. A fully qualified resource
                name is of the form "package:type/entry". The first two components (package and type)
                are optional if defType and defPackage, respectively, are specified here.

                Note: use of this function is discouraged. It is much more efficient to retrieve
                resources by identifier than by name.

            Parameters
                name String: The name of the desired resource.

                defType String: Optional default resource type to find, if "type/" is
                    not included in the name. Can be null to require an explicit type.

                defPackage String: Optional default package to find, if "package:" is not
                    included in the name. Can be null to require an explicit package.
            Returns
                int : int The associated resource identifier. Returns 0 if no such resource
                    was found. (0 is not a valid resource ID.)
        */
        // Get the string resource id by name
        int resourceId = context.getResources().getIdentifier(resourceName, "string", packageName);

        if (resourceId != 0)
            return CustomApplication.getContext().getString(resourceId);

        // Return the string value
        return resourceName;
    }

    public static Drawable getDrawableFromResourcesByName(Context context, String drawableName) {
        /*
            getPackageName()
                Return the name of this application's package.
        */
        // Get the application package name
        String packageName = context.getPackageName();

        /*
            getResources()
                Return a Resources instance for your application's package.
        */
        /*
            public int getIdentifier (String name, String defType, String defPackage)
                Return a resource identifier for the given resource name. A fully qualified resource
                name is of the form "package:type/entry". The first two components (package and type)
                are optional if defType and defPackage, respectively, are specified here.

                Note: use of this function is discouraged. It is much more efficient to retrieve
                resources by identifier than by name.

            Parameters
                name String: The name of the desired resource.

                defType String: Optional default resource type to find, if "type/" is
                    not included in the name. Can be null to require an explicit type.

                defPackage String: Optional default package to find, if "package:" is not
                    included in the name. Can be null to require an explicit package.
            Returns
                int : int The associated resource identifier. Returns 0 if no such resource
                    was found. (0 is not a valid resource ID.)
        */
        // Get the drawable id by name
        int resourceId = context.getResources().getIdentifier(drawableName, "drawable", packageName);

        if (resourceId != 0)
            return CustomApplication.getContext().getDrawable(resourceId);

        // Return the string value
        return null;
    }

    public static int getViewIDFromResourcesByName(Context context, String controlName) {
        /*
            getPackageName()
                Return the name of this application's package.
        */
        // Get the application package name
        String packageName = context.getPackageName();

        /*
            getResources()
                Return a Resources instance for your application's package.
        */
        /*
            public int getIdentifier (String name, String defType, String defPackage)
                Return a resource identifier for the given resource name. A fully qualified resource
                name is of the form "package:type/entry". The first two components (package and type)
                are optional if defType and defPackage, respectively, are specified here.

                Note: use of this function is discouraged. It is much more efficient to retrieve
                resources by identifier than by name.

            Parameters
                name String: The name of the desired resource.

                defType String: Optional default resource type to find, if "type/" is
                    not included in the name. Can be null to require an explicit type.

                defPackage String: Optional default package to find, if "package:" is not
                    included in the name. Can be null to require an explicit package.
            Returns
                int : int The associated resource identifier. Returns 0 if no such resource
                    was found. (0 is not a valid resource ID.)
        */

        int resourceId = context.getResources().getIdentifier(controlName, "id", packageName);
        return resourceId;

    }
}
