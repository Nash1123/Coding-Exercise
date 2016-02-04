/*
 * MATLAB Compiler: 4.18.1 (R2013a)
 * Date: Sun Jan 31 21:39:01 2016
 * Arguments: "-B" "macro_default" "-W" "java:plotter,Plotter" "-T" "link:lib" "-d" 
 * "C:\\Users\\user\\Desktop\\Java\\Matlab\\Projects\\plotter\\src" "-w" 
 * "enable:specified_file_mismatch" "-w" "enable:repeated_file" "-w" 
 * "enable:switch_ignored" "-w" "enable:missing_lib_sentinel" "-w" "enable:demo_license" 
 * "-v" "class{Plotter:C:\\Users\\user\\Desktop\\Java\\Matlab\\Projects\\drawplot.m}" 
 */

package plotter;

import com.mathworks.toolbox.javabuilder.*;
import com.mathworks.toolbox.javabuilder.internal.*;

/**
 * <i>INTERNAL USE ONLY</i>
 */
public class PlotterMCRFactory
{
   
    
    /** Component's uuid */
    private static final String sComponentId = "plotter_875C5ECFC8DF8BB549AE9B1A20E7F542";
    
    /** Component name */
    private static final String sComponentName = "plotter";
    
   
    /** Pointer to default component options */
    private static final MWComponentOptions sDefaultComponentOptions = 
        new MWComponentOptions(
            MWCtfExtractLocation.EXTRACT_TO_CACHE, 
            new MWCtfClassLoaderSource(PlotterMCRFactory.class)
        );
    
    
    private PlotterMCRFactory()
    {
        // Never called.
    }
    
    public static MWMCR newInstance(MWComponentOptions componentOptions) throws MWException
    {
        if (null == componentOptions.getCtfSource()) {
            componentOptions = new MWComponentOptions(componentOptions);
            componentOptions.setCtfSource(sDefaultComponentOptions.getCtfSource());
        }
        return MWMCR.newInstance(
            componentOptions, 
            PlotterMCRFactory.class, 
            sComponentName, 
            sComponentId,
            new int[]{8,1,0}
        );
    }
    
    public static MWMCR newInstance() throws MWException
    {
        return newInstance(sDefaultComponentOptions);
    }
}
