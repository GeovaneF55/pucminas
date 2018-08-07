clear;
close all;

% Load  Images
imPath1 = fullfile(vl_root, 'data', 'a.jpg');
im1 = imread(imPath1);
imshow(im1);

figure;
imPath2 = fullfile(vl_root, 'data', 'b.jpg');
im2 = imread(imPath2);
imshow(im2);

im1_orig=im1;
im2_orig=im2;

% Convert into float array
im1 = im2single(rgb2gray(im1));
im2 = im2single(rgb2gray(im2));

% run SIFT
[frames1 , descrs1] = vl_sift(im1);
[frames2 , descrs2] = vl_sift(im2);

% visualize key points
figure;
imagesc(im1);
colormap gray; hold on;
vl_plotframe(frames1);

% Visualize Descriptors
vl_plotsiftdescriptor(descrs1(:,432),frames1(:,432));

% Matching code
[matches,scores]=vl_ubcmatch(descrs1,descrs2);

% Visualize matches
figure;
subplot (1,2,1);
imshow (uint8(im1_orig));
hold on;
plot (frames1(1,matches(1,:)), frames1(2, matches(1,:)), 'b*');

subplot (1,2,2);
imshow (uint8(im2_orig));
hold on;
plot (frames2(1, matches(2,:)), frames2(2, matches (2,:)), 'r*');

% Visualize matches with lines
figure;
imagesc(cat(2, im1_orig, im2_orig)) ;

xa = frames1(1,matches(1,:)) ;
xb = frames2(1,matches(2,:)) + size(im2_orig,2) ;
ya = frames1(2,matches(1,:)) ;
yb = frames2(2,matches(2,:)) ;

hold on ;
h = line([xa ; xb], [ya ; yb]) ;
set(h,'linewidth', 1, 'color', 'b') ;

vl_plotframe(frames1(:,matches(1,:))) ;
frames2(1,:) = frames2(1,:) + size(im2_orig,2) ;
vl_plotframe(frames2(:,matches(2,:))) ;
axis image off ;
