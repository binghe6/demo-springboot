//package com.binghe.demo.thread;
//
//import lombok.extern.slf4j.Slf4j;
//
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Component;
//
//@Component
//@Slf4j
//public class Hello {
//    
//	/**
//	 * 有返回值的
//	 * @param name
//	 */
////	@Async
////    public ListenableFuture<String> sayHello(String name) {
////        String res = name + ":Hello World!";
////        log.info(res);
////        try {
////			Thread.sleep(10000L);
////		} catch (InterruptedException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////        return new AsyncResult<>(res);
////    }
//	
//	/**
//	 * 启用线程池中的线程
//	 * @param name
//	 */
//	@Async
//    public void sayHello(String name) {
//		try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        log.info(name + ":Hello World!");
//    }
//}