/**
 * MTL File Compiler for use with the launcher
 * This class is responsible to compil mtl files into
 * emtl
 * This class is used during the build of the module.
 */

package mtlcompiler;

import java.io.File;
import java.util.LinkedHashSet; 
import org.eclipse.acceleo.internal.parser.compiler.AcceleoProject;
import org.eclipse.acceleo.internal.parser.compiler.AcceleoParser;
import org.eclipse.acceleo.internal.parser.compiler.AcceleoProjectClasspathEntry;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.uml2.uml.resource.UMLResource;

/**
 * mtl to emtl compiler 
 * - The source path where are located mtl files must be set as first args
 * when invoking this class 
 * - The destination path must be set as second arg 
 */
public class MtlCompiler
{
    public static void main(String[] args)
    {
        if(args.length != 3) {
            System.out.println("The mtl source and dest. paths must be specified as first argument and second arg");
            System.exit(0);
        }
        File projectFolder = new File(args[0]);
        LinkedHashSet<AcceleoProjectClasspathEntry> mtlSources 
            = new LinkedHashSet<AcceleoProjectClasspathEntry>();
        mtlSources.add(new AcceleoProjectClasspathEntry(
            new File(args[1]),
            new File(args[2])
        ));
        AcceleoProject acceleoProject = new AcceleoProject(
            projectFolder,
            mtlSources
        );
        EPackage.Registry.INSTANCE.put(org.eclipse.uml2.uml.UMLPackage.eINSTANCE.getNsURI(), org.eclipse.uml2.uml.UMLPackage.eINSTANCE);
            AcceleoParser acceleoParser = new AcceleoParser(acceleoProject, false, true);
        acceleoParser.buildAll(new BasicMonitor());
    }
}
