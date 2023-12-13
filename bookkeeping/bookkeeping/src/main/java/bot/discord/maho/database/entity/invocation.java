package bot.discord.maho.database.entity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class invocation<T> implements InvocationHandler {
	private T proxyClass;

	
	public invocation() {}
	public invocation(T proxyClass) {
		this.proxyClass = proxyClass;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

}
