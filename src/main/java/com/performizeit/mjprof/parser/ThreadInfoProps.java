/*
       This file is part of mjprof.

        mjprof is free software: you can redistribute it and/or modify
        it under the terms of the GNU General Public License as published by
        the Free Software Foundation, either version 3 of the License, or
        (at your option) any later version.

        mjprof is distributed in the hope that it will be useful,
        but WITHOUT ANY WARRANTY; without even the implied warranty of
        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
        GNU General Public License for more details.

        You should have received a copy of the GNU General Public License
        along with mjprof.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.performizeit.mjprof.parser;

public interface ThreadInfoProps {
    public static final String NAME = "name";     // the name of the thread
    public static final String PRIO = "prio";     //priority
    public static final String CPUNS = "cpu_ns";       // amount of cpu consumed by thread
    public static final String WALL = "wall_ms";       // amount of cpu consumed by thread
    public static final String CPU_PREC = "%cpu";
    public static final String TID = "tid";       // java thread id
    public static final String NID = "nid";       //  native thread id
    public static final String STACK = "stack";   //  the stack of the thread or the profile of more than one thread
    public static final String STATUS = "status";
    public static final String STATE = "state";
    public static final String LOS = "los";        //locked ownable synchronizers
    public static final String DAEMON = "daemon";  // daemon  true / false
    public static final String COUNT = "count";    // number of actual stacks this profile represent
}