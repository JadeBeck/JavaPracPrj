package smartnurse.smartnursepracpjt.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
//이렇게 컴포넌트스캔 되도록 애노테이션 걸어 둘 수도 있지만, 얘는 뭐.. Service, Repository 이런 전형적인 애들이 아닌 "APO"니까. 확실히 한눈에 인지되도록 Config 파일에 등록
public class TimeTraceAOP {

    @Around("execution(* smartnurse.smartnursepracpjt..*(..))")  //공통 관심 사항(cross-cutting concern) 타겟팅을 해줌. 지금은 "패키지 하위에 다 적용해!!"임
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
            //위의 한줄은 아래 두 줄이 인라인화 됨
            //Object result = joinPoint.prceed();
            //return result;
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + timeMs + "ms");
        }
    }
}