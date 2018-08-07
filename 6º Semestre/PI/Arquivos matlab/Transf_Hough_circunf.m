clear;
close all;

A = imread('coins.png');
imshow(A)
[m,n]=size(A)


% Find all the circles with radius r pixels in the range [15, 30].

[centers, radii, metric] = imfindcircles(A,[15 30]);
[centers, radii, metric]

%   [CENTERS, RADII, METRIC] = imfindcircles(A, RADIUS_RANGE) finds circles with
%     radii (in pixels) in the search range specified by RADIUS_RANGE. RADIUS_RANGE is a
%     two-element vector [MIN_RADIUS MAX_RADIUS], where MIN_RADIUS and
%     MAX_RADIUS have integer values. 
%   A can be grayscale, RGB or binary image. CENTERS is a P-by-2 matrix with
%     X-coordinates of the circle centers in the first column and the
%     Y-coordinates in the second column.
%   The estimated radii, in pixels, for the
%     circles are returned in the column vector RADII. 
%   It also returns the magnitude of the accumulator array peak associated with
%     each circle in the column vector METRIC. CENTERS and RADII are
%     sorted in descending order of their corresponding METRIC values.

%Retain the 8 strongest circles according to the metric values.

i=10;

centersStrong = centers(1:i,:); 
radiiStrong = radii(1:i);
metricStrong = metric(1:i);

% Draw the five strongest circle perimeters over the original image.

viscircles(centersStrong, radiiStrong,'EdgeColor','b');