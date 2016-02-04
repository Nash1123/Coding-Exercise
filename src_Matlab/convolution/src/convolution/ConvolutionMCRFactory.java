/*
 * MATLAB Compiler: 4.18.1 (R2013a)
 * Date: Sun Jan 31 20:13:05 2016
 * Arguments: "-B" "macro_default" "-W" "java:convolution,Convolution" "-T" "link:lib" 
 * "-d" "C:\\Users\\user\\Desktop\\Java\\Matlab\\Projects\\convolution\\src" "-w" 
 * "enable:specified_file_mismatch" "-w" "enable:repeated_file" "-w" 
 * "enable:switch_ignored" "-w" "enable:missing_lib_sentinel" "-w" "enable:demo_license" 
 * "-v" 
 * "class{Convolution:C:\\Users\\user\\Desktop\\Java\\Matlab\\Projects\\convolution.m}" 
 */

package convolution;

import com.mathworks.toolbox.javabuilder.*;
import com.mathworks.toolbox.javabuilder.internal.*;

/**
 * <i>INTERNAL USE ONLY</i>
 */
public class ConvolutionMCRFactory
{
   
    
    /** Component's uuid */
    private static final String sComponentId = "convolution_5A0A5F27EE6A4DCB92741AA0486AD0B8";
    
    /** Component name */
    private static final String sComponentName = "convolution";
    
   
    /** Pointer to default component options */
    private static final MWComponentOptions sDefaultComponentOptions = 
        new MWComponentOptions(
            MWCtfExtractLocation.EXTRACT_TO_CACHE, 
            new MWCtfClassLoaderSource(ConvolutionMCRFactory.class)
        );
    
    
    private ConvolutionMCRFactory()
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
            ConvolutionMCRFactory.class, 
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
