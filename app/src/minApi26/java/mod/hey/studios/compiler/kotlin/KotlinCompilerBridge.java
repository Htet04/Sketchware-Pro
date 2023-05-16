package mod.hey.studios.compiler.kotlin;

import java.io.File;

import a.a.a.BuiltInLibraryManager;
import a.a.a.Dp;
import a.a.a.yq;
import mod.agus.jcoderz.lib.FileUtil;
import mod.jbk.build.BuildProgressReceiver;
import mod.jbk.build.BuiltInLibraries;

public class KotlinCompilerBridge {

    public static void compileKotlinCodeIfPossible(BuildProgressReceiver receiver, Dp dp) throws Throwable {
        if (KotlinCompilerUtil.areAnyKtFilesPresent(dp)) {
            receiver.onProgress("Kotlin is compiling...");
            new KotlinCompiler(dp).compile();
        }
    }

    public static void maybeAddKotlinBuiltInLibraryDependenciesIfPossible(Dp dp, BuiltInLibraryManager builtInLibraryManager) {
        if (KotlinCompilerUtil.areAnyKtFilesPresent(dp)) {
            builtInLibraryManager.addLibrary(BuiltInLibraries.KOTLIN_STDLIB);
        }
    }

    public static void maybeAddKotlinFilesToClasspath(StringBuilder classpath, yq workspace) {
        if (FileUtil.isExistFile(workspace.compiledClassesPath)) {
            classpath.append(workspace.compiledClassesPath);
            classpath.append(":");
        }
    }

    public static String getKotlinHome(yq workspace) {
        return workspace.binDirectoryPath + File.separator + "kotlin_home";
    }
}
