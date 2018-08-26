package nc.unc.importparcoursup.commons.utils;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

public class MyReflectionUtils {
    /**
     * 
     * @return list class attributes
     */
    public static String[] getFieldsFromClass(Class<?> claz) {
	return FieldUtils.getAllFieldsList(claz)
		.stream()
		.map(f -> f.getName())
		.filter(name -> !"id".equals(name))
		.filter(name -> !"TABLE_NAME".equals(name))
		.toArray(String[]::new);
    }

    /**
     * 
     * @param fields
     * @return sql request for each field: Example, table people, fields first_name,
     *         last_name -> "INSERT INTO people (first_name, last_name) VALUES
     *         (:firstName, :lastName)"
     */
    public static String generateSqlRequestFromFields(String table, String[] fields) {
	String ret = "INSERT INTO " + table + " (";
	ret += StringUtils.join(fields, ","); // first_name, last_name
	ret += ") VALUES (";
	ret += StringUtils.join(Arrays.stream(fields)
		.map(f -> ":" + f)
		.toArray(), ","); // prefix each field with ':' -> :firstName, :lastName
	ret += ")";
	return ret;
    }

    /**
     * 
     * @return display all attribute and values from object
     * @throws IntrospectionException
     */
    public static String ObjectToStr(Object obj) {
	try {
	
	    return Arrays.stream(Introspector.getBeanInfo(obj.getClass())
	    	.getPropertyDescriptors())
	    	.filter(property -> property.getReadMethod() != null && !"class".equals(property.getName()))
	    	.map(property -> propertyToStr(obj,property))
	    	.collect(Collectors.joining());
	} catch (IntrospectionException e) {
	    throw new RuntimeException(e);
	}
    }

    private static String propertyToStr(Object obj, PropertyDescriptor prop) {
	try {
	    String value = ""+prop.getReadMethod().invoke(obj);
	    String valueToDisplay = StringUtils.isEmpty(value)?"empty":value;
	    return " " +prop.getName() + ":" + valueToDisplay;
	} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
	    throw new RuntimeException(e);
	}
    }
}
