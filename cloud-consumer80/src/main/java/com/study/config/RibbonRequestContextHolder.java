package com.study.config;

public class RibbonRequestContextHolder  {
    private static ThreadLocal<RibbonRequestContext> holder=new ThreadLocal<RibbonRequestContext>() {
        @Override
        protected RibbonRequestContext initialValue() {
            return new RibbonRequestContext();
        }
    };

        public static RibbonRequestContext getCurrentContext(){
            return holder.get();
        }
        public static void setCurrentContext(RibbonRequestContext context){
            holder.set(context);
        }
        public static  void clearContext(){
            holder.remove();
        }
}
