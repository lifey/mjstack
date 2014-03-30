/*
       This file is part of mjstack.

        mjstack is free software: you can redistribute it and/or modify
        it under the terms of the GNU General Public License as published by
        the Free Software Foundation, either version 3 of the License, or
        (at your option) any later version.

        mjstack is distributed in the hope that it will be useful,
        but WITHOUT ANY WARRANTY; without even the implied warranty of
        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
        GNU General Public License for more details.

        You should have received a copy of the GNU General Public License
        along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.performizeit.mjstack.plugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;

import com.performizeit.mjstack.api.JStackFilter;
import com.performizeit.mjstack.api.JStackMapper;
import com.performizeit.mjstack.api.Plugin;
import com.performizeit.mjstack.parser.JStackMetadataStack;


public class PluginUtils {

	private static final String MAPPER_INTERFACE = "com.performizeit.mjstack.api.JStackMapper";
	private static final String FILTER_INTERFACE = "com.performizeit.mjstack.api.JStackFilter";
	private static final String BASE_PLUGIN_INTERFACE = "com.performizeit.mjstack.api.BasePlugin";
	private static final String TERMINAL_INTERFACE = "com.performizeit.mjstack.api.JStackTerminal";
	private static final String COMPARATORS_INTERFACE = "com.performizeit.mjstack.api.JStackComparator";


	public static Object initObj(Class<?> clazz, Class[] paramTypes,List<String> params) throws NoSuchMethodException, InstantiationException,	IllegalAccessException, InvocationTargetException {
		Constructor<?> constructor = clazz.getConstructor(paramTypes);
		return constructor.newInstance(params.toArray());
	}


	//conParameter - constructor parameters
	public static HashMap<String,Class> getAllPlugins() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ClassNotFoundException{
		HashMap<String,Class> map = new HashMap<String,Class>();
		//TODO
		Reflections reflections = new Reflections("com.performizeit");
		Set<Class<?>> annotatedPlugin = reflections.getTypesAnnotatedWith(Plugin.class);

		for(Class cla :annotatedPlugin){
			if(isImplementsMapper(cla) ||isImplementsFilter(cla) ||isImplementsTerminal(cla) || isImplementsComparators(cla) ){
				String helpLine=invokeGetHelpLine(cla);	
				map.put(helpLine, cla);
			}else{
				System.out.println("class " + cla.getName() + " needs to extend BasePlugin child");
			}
		}
		return map;
	}

	private static String invokeGetHelpLine(Class<?> cla)  {
	    Plugin pluginAnnotation = cla.getAnnotation(Plugin.class);
        return pluginAnnotation.description();
	}

	private static boolean isImplementsPlugin(Class<?> cla,String pluginType) {
		Class[] k = cla.getInterfaces();
		for(int i=0;i<k.length;i++){
			if(k[i].getName().equals(pluginType))
				return true;
		}
		return false;
	}
	public static boolean isImplementsMapper(Class<?> cla) {
		return isImplementsPlugin(cla,MAPPER_INTERFACE);
	}
	public static boolean isImplementsFilter(Class<?> cla) {
		return isImplementsPlugin(cla,FILTER_INTERFACE)|| isImplementsPlugin(cla.getSuperclass(), FILTER_INTERFACE);
	}

	public static boolean isImplementsTerminal(Class<?> cla) {
		return isImplementsPlugin(cla,TERMINAL_INTERFACE) || isImplementsPlugin(cla.getSuperclass(), TERMINAL_INTERFACE);
	}

	public static boolean isImplementsComparators(Class cla) {
		return isImplementsPlugin(cla, COMPARATORS_INTERFACE) || isImplementsPlugin(cla.getSuperclass(), COMPARATORS_INTERFACE);
	}
	
	
	
}
