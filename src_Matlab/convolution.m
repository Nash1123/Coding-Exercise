function CDF = convolution(cdf_current, cdf_once, times)
    CDF = cdf_current;
    for i = 1: times
        CDF = conv(CDF, cdf_once);
    end
end

