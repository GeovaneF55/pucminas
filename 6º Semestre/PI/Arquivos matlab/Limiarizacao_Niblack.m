
clear
close all;

x = imread('girl2.ras');
        figure;
        imshow(x); title('x - Original');
    
        %   Resize the image.  
        
        size(x)
        
        x1 = imresize(x,[500 800]);
        size(x1)
        figure;
        imshow(x1);
        title('x1 - Resized');
        
        x2 = imadjust(x1, [0.2,0.8],[0.5,1.0]);
        figure
        imshow(x2);title('x2 - Transformação de contraste');
        
%    J = imadjust(I,[LOW_IN; HIGH_IN],[LOW_OUT; HIGH_OUT]) maps the values
%     in intensity image I to new values in J such that values between LOW_IN
%     and HIGH_IN map to values between LOW_OUT and HIGH_OUT. Values below
%     LOW_IN and above HIGH_IN are clipped; that is, values below LOW_IN map
%     to LOW_OUT, and those above HIGH_IN map to HIGH_OUT. You can use an
%     empty matrix ([]) for [LOW_IN; HIGH_IN] or for [LOW_OUT; HIGH_OUT] to
%     specify the default of [0 1]. If you omit the argument, [LOW_OUT;
%     HIGH_OUT] defaults to [0 1].
    
        %   HSV plane, extracting the value part.
        z = rgb2hsv(x2); 
        size(z) % 3 componentes: RGB
        
%     HSV = rgb2hsv(RGB) converts the RGB image RGB (3-D array) to the
%     equivalent HSV image HSV (3-D array).
        
        v = z(:,:,3); % componente V (value)
        size(v)
        
        v1 = imadjust(v);
    
        %   Finding the mean and standard deviation.  
        m = mean(v1(:))
        s = std(v1(:))
        k = -.4;
        value = m+ k*s
        temp = v; % Componente value (V, do HSV)
    
       
        
%   BW = NIBLACK(IMAGE, [M N], K, OFFSET, PADDING) performs local
%   thresholding with M-by-N neighbourhood (default is 3-by-3). The default
%   value for K is -0.2. The default value of OFFSET is 0, which 
%   coresponds to the original Niblack implementation. To deal with border 
%   pixels the image is padded with one of PADARRAY options (default is 
%   'replicate').
%       
%   Example
%   -------
%       imshow(niblack(imread('eight.tif'), [25 25], -0.2, 10));

        gray=rgb2gray(x2);
        BW1 = niblack(gray); %janela defaul é 3x3
        figure
        imshow(BW1)
         title('Niblack Result using m e sigma locais, com janela 3x3 (default) e k=-0.2 (default)');
        
        BW2 = niblack(gray,[25 25]);
        figure
        imshow(BW2)
         title('Niblack Result using m e sigma locais, com janela 25x25 e k=-0.2 (default)');
         
        BW2 = niblack(gray,[100 100]);
        figure
        imshow(BW2)
         title('Niblack Result using m e sigma locais, com janela 100x100 e k=-0.2 (default)');
         
        BW3 = niblack(gray,[200 200]);
        figure
        imshow(BW3)
         title('Niblack Result using m e sigma locais, com janela 200x200 e k=-0.2 (default)'); 
        