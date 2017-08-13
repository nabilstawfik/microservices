/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservice.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author nabil
 */
public class CollectionUtils {

    private CollectionUtils() {
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> cast(List<?> source) {
        return (List<T>) source;
    }

    @SuppressWarnings("unchecked")
    public static <T> Collection<T> cast(Collection<?> source) {
        return (Collection<T>) source;
    }

    @SuppressWarnings("unchecked")
    public static <T> ArrayList<T> cast(ArrayList<?> source) {
        return (ArrayList<T>) source;
    }

    @SuppressWarnings("unchecked")
    public static <T> Set<T> cast(Set<?> source) {
        return (Set<T>) source;
    }

    @SuppressWarnings("unchecked")
    public static <T> HashSet<T> cast(HashSet<?> source) {
        return (HashSet<T>) source;
    }

}
