# Standalone compilation ROADMAP 

## Usage final souhaité :
```
java -jar umltophp.jar fichier.uml rep_dest/
```

## Etapes de compilation :

1. Suppression du dossier de build __DONE
2. Creation du dossier de build __DONE
3. Compilation du projet (main.java) __DONE
4. Copie des libs nécéssaires (eclipse, acceleo, equinox etc..)
5. Lancement compilation des fichiers mtl. __DONE
6. Creation du jar final depuis dossier de build __DONE

-> Extra : gestion du umltophp.properties __DONE

Compiler Launcher:

-> Instancier une classe org.eclipse.acceleo.internal.parser.compiler.AcceleoProject avec le path contenant les .mtl
-> Instancier une classe org.eclipse.acceleo.internal.parser.compiler.AcceleoParser  


Infos comp :

Lancement du générateur :
cd classes/
java -classpath ../lib/*: umltophp.main.Main

### Structure de fichiers du jar

umltophp
    main
        #Fichiers#
META-INF
    MANIFEST.MF
umltophp.properties


