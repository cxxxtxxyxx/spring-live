package shop.mtcoding._core;

import shop.mtcoding.annotation.Component;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class ComponentScanner {

    // 클래스를 스캔하는 메소드
    public static Set<Class> scanClass(String pkg) throws Exception {
        // 현재 패키지 위치를 받아옴 (shop.mtcoding._core)
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        // 클래스들을 담을 컬렉션
        Set<Class> classes = new HashSet<>();

        // 매개변수로 전달받은 패키지 위치
        URL packageUrl = classLoader.getResource(pkg.replace(".", "/"));

        // 지정한 패키지의 모든 파일 불러오기
        File packageDirectory = new File(packageUrl.toURI());

        // 패키지의 클래스 스캔
        scanPackage(pkg, packageDirectory, classes);

        return classes;
    }

    // 패키지의 클래스 스캔
    private static void scanPackage(String pkg, File directory, Set<Class> classes) throws Exception {

        for (File file : directory.listFiles()) {
            String fileName = file.getName();

            // 해당 파일이 패키지일경우
            if (file.isDirectory()) {

                // 재귀적으로 사용하여 하위 패키지를 스캔한다.
                scanPackage(pkg + "." + fileName, file, classes);

            // 해당 파일이 클래스 일경우
            } else if (fileName.endsWith(".class")) {

                String className = pkg + "." + fileName.replace(".class", "");
                Class cls = Class.forName(className);

                // 해당 클래스가 어노테이션이면 검사하지 않는다.
                if(cls.isAnnotation()){
                    return;
                }

                // 해당 클래스의 어노테이션이 메타 어노테이션으로 Component를 갖고 있거나, Component 어노테이션을 갖고 있을 경우
                if (hasMetaAnnotation(cls, Component.class) || cls.isAnnotationPresent(Component.class)) {
                    // 매개변수로 전달받은 컬렉션에 저장 (해당 클래스가 어노테이션이 아닐 경우만)
                    classes.add(cls);
                }
            }
        }
    }

    // 클래스의 어노테이션의 메타데이터를 확인하는 메소드
    private static boolean hasMetaAnnotation(Class<?> cls, Class<? extends Annotation> metaAnnotation) {
        // 클래스의 어노테이션들을 갖고온다.
        for (Annotation annotation : cls.getDeclaredAnnotations()) {
            // 해당 어노테이션이 매개변수로 전달받은 어노테이션을 메타데이터로 가지고 있으면
            if (annotation.annotationType().isAnnotationPresent(metaAnnotation)) {
                // true 리턴
                return true;
            }
        }
        return false;
    }


}
