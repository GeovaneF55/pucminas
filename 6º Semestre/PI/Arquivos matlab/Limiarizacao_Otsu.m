close all;
clear;

% Read a grayscale image into the workspace.

I = imread('coins.png');

% Calculate a threshold using graythresh. The threshold is normalized to the range [0, 1].

% graythresh Global image threshold using Otsu's method.
%     
%     LEVEL = graythresh(I) computes a global threshold (LEVEL) that can be
%     used to convert an intensity image to a binary image with IM2BW. LEVEL
%     is a normalized intensity value that lies in the range [0, 1].
%     graythresh uses Otsu's method, which chooses the threshold to minimize
%     the intraclass variance of the thresholded black and white pixels.
%     
%     [LEVEL EM] = graythresh(I) returns effectiveness metric, EM, as the
%     second output argument. It indicates the effectiveness of thresholding
%     of the input image and it is in the range [0, 1]. The lower bound is
%     attainable only by images having a single gray level, and the upper
%     bound is attainable only by two-valued images.

level = graythresh(I)
level


% Convert the image into a binary image using the threshold.

BW = im2bw(I,level);

%  im2bw Convert image to binary image by thresholding.
%     im2bw produces binary images from indexed, intensity, or RGB images. To do
%     this, it converts the input image to grayscale format (if it is not already
%     an intensity image), and then converts this grayscale image to binary by
%     thresholding. The output binary image BW has values of 1 (white) for all
%     pixels in the input image with luminance greater than LEVEL and 0 (black)
%     for all other pixels. (Note that you specify LEVEL in the range [0,1], 
%     regardless of the class of the input image.)

% Display the original image next to the binary image.     

figure
imshowpair(I,BW,'montage'),title('Mostra diferença entre imagens colocando uma ao lado da outra') 

%     imshowpair Compare differences between images.
%     H = imshowpair(A,B) creates a visualization of the differences between
%     images A and B.  If A and B are different sizes, the smaller dimensions are
%     padded with zeros such that the 2 image are the same size before
%     display. H is a handle to the HG image object created by imshowpair.

% 
%     imshowpair(...,METHOD) displays the differences between images A and B
%     using the visualization style specified by METHOD.  Values of METHOD
%     can be:
%  
%        'falsecolor' : Create a composite RGB image showing A and B overlayed
%                       in different color bands. This is the default.
%        'blend'      : Overlay A and B using alpha blending.
%        'diff'       : Difference image created from A and B.
%        'montage'    : Put A and B next to each other in the same image.

figure
imshowpair(I,BW,'falsecolor');title('Mostra diferença entre imagens usando falsecolor') 

figure
imshowpair(I,BW,'blend');title('Mostra diferença entre imagens usando blend') 

figure
imshowpair(I,BW,'blend');title('Mostra diferença entre imagens usando diff') 
