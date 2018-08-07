% Compute and Display Hough Transform

clear;
close all


% Read an image, and convert it to grayscale.
RGB = imread('gantrycrane.png');
figure
imshow(RGB)
I  = rgb2gray(RGB);
figure
imshow(I)
[M,N]=size(I)

% Extract edges
BW = edge(I,'canny');
figure
imshow(BW);title('Canny');

% Calculate the Hough transform over a limited range of angles
%[H,T,R] = hough(BW,'RhoResolution',0.5,'Theta',-90:0.5:89.5); %Default: RhoResolution=0.5 e Theta varia de -90 a 89
[H,T,R] = hough(BW);
%[H,T,R] = hough(BW,'RhoResolution',0.5,'ThetaResolution',0.5);


% Display the Hough transform
       figure;
       subplot(2,1,1);
       imshow(RGB);
       title('gantrycrane.png');
  
       subplot(2,1,2); 
       imshow(imadjust(mat2gray(H)),'XData',T,'YData',R,...
              'InitialMagnification','fit');
          
%               'InitialMagnification'   A numeric scalar value, or the text string 'fit',
%                              that specifies the initial magnification used to 
%                              display the image. When set to 100, the image is 
%                              displayed at 100% magnification. When set to 
%                              'fit' imshow scales the entire image to fit in 
%                              the window.
%  
%                              On initial display, the entire image is visible.
%                              If the magnification value would create an image 
%                              that is too large to display on the screen,  
%                              imshow warns and displays the image at the 
%                              largest magnification that fits on the screen.

% 
%  mat2gray Convert matrix to intensity image.
%     I = mat2gray(A,[AMIN AMAX]) converts the matrix A to the intensity image I.
%     The returned matrix I contains values in the range 0.0 (black) to 1.0 (full
%     intensity or white).  AMIN and AMAX are the values in A that correspond to
%     0.0 and 1.0 in I.  Values less than AMIN become 0.0, and values greater than
%     AMAX become 1.0.

%  imadjust Adjust image intensity values or colormap.
%     J = imadjust(I) maps the values in intensity image I to new values in J
%     such that 1% of data is saturated at low and high intensities of I.

       title('Limited theta range Hough transform of gantrycrane.png');
       xlabel('\theta (degrees)'), ylabel('\rho');
       axis on, axis normal;
      
       
       
% Or: Calculate the Hough transform over a limited range of angles.
[H1,T1,R1] = hough(BW,'Theta',44:0.5:46);
figure
imshow(imadjust(mat2gray(H1)),'XData',T1,'YData',R1,...
   'InitialMagnification','fit');
title('Limited Theta Range Hough Transform of Gantrycrane Image');
xlabel('\theta (degrees)')
ylabel('\rho');
axis on, axis normal;
colormap(gca,hot)

% Find the peaks in the Hough transform matrix, H, using the houghpeaks function.

P = houghpeaks(H,20,'threshold',ceil(0.3*max(H(:)))); % retorna vetor com [rho,theta] dos 20 maiores picos de H

% Superimpose a plot on the image of the transform that identifies the peaks.

figure
imshow(imadjust(mat2gray(H)),'XData',T,'YData',R,...
              'InitialMagnification','fit');
          xlabel('\theta (degrees)')
ylabel('\rho')
axis on
axis normal 
hold on
colormap(gca,hot)
x = T(P(:,2));
y = R(P(:,1));
plot(x,y,'s','color','black');
       
% Find lines in the image using the houghlines function.

lines = houghlines(BW,T,R,P,'FillGap',5,'MinLength',20);

%  houghlines Extract line segments based on Hough transform.
%     LINES = houghlines(BW, THETA, RHO, PEAKS) extracts line segments
%     in the image BW associated with particular bins in a Hough 
%     transform.  THETA and RHO are vectors returned by function HOUGH.
%     Matrix PEAKS, which is returned by function HOUGHPEAKS,
%     contains the row and column coordinates of the Hough transform 
%     bins to use in searching for line segments. 

%   houghlines returns
%     LINES structure array whose length equals the number of merged
%     line segments found. Each element of the structure array has
%     these fields: 
%  
%        point1  End-point of the line segment; two-element vector
%        point2  End-point of the line segment; two-element vector
%        theta   Angle (in degrees) of the Hough transform bin
%        rho     Rho-axis position of the Hough transform bin



% Como exemplo:
lines(1).point1 %(coordenadas do ponto 1)
lines(1).point2 %(coordenadas do ponto 2)
lines(1).theta % theta da reta (coordenadas polares)
lines(1).rho % rho da reta (coordenadas polares)

%Create a plot that displays the original image with the lines superimposed on it.

figure, imshow(I), hold on
max_len = 0;
for k = 1:length(lines)
   xy = [lines(k).point1; lines(k).point2];
   plot(xy(:,1),xy(:,2),'LineWidth',2,'Color','green');

   % Plot beginnings and ends of lines
   plot(xy(1,1),xy(1,2),'x','LineWidth',2,'Color','yellow');
   plot(xy(2,1),xy(2,2),'x','LineWidth',2,'Color','cyan');

   % Determine the endpoints of the longest line segment
   len = norm(lines(k).point1 - lines(k).point2);
   if ( len > max_len)
      max_len = len;
      xy_long = xy;
   end
end
xy_long % retorna coordenadas dos pontos extremos da linha mais longa.
max_len
% highlight the longest line segment
plot(xy_long(:,1),xy_long(:,2),'LineWidth',2,'Color','red'); % colore linhas mais longa de vermelho
       






